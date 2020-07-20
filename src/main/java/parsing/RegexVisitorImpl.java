package parsing;

import antlr.RegexBaseVisitor;
import antlr.RegexParser;
import automaton.nfa.Nfa;
import automaton.transition.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegexVisitorImpl extends RegexBaseVisitor<Nfa> {
    private RegexConfig regexConfig;
    private Object storage;

    private Nfa parseMany(Collection<RegexParser.ExprContext> expressions) {
        return Nfa.concat(expressions.stream().map(this::visitExpr).collect(Collectors.toList()));
    }

    private Nfa parseMany1(Collection<RegexParser.Expr1Context> expressions) {
        return Nfa.concat(expressions.stream().map(this::visitExpr1).collect(Collectors.toList()));
    }

    @Override
    public Nfa visitStart(RegexParser.StartContext ctx) {
        regexConfig = new RegexConfig(ctx.params().getText());
        Nfa result = parseMany(ctx.expr());
        result.close();
        return result;
    }

    @Override
    public Nfa visitCharset(RegexParser.CharsetContext ctx) {
        Transition transition = CollectionTransition.merge(
                ctx.charsetValues().stream().map(this::parseCharsetValues).collect(Collectors.toList()));
        if (ctx.CAP() != null) {
            transition = new ComplementTransition(transition);
        }
        return new Nfa(transition);
    }

    public Transition parseCharsetRange(RegexParser.CharsetRangeContext ctx) { // TODO: rework for visit charset
        String text = ctx.getText();
        int hyphenPos = text.indexOf('-');
        Transition transition = new RangeTransition(text.substring(0, hyphenPos), text.substring(hyphenPos + 1));
        if (regexConfig.isCaseInsensitive()) {
            transition = new CollectionTransition(Stream.concat(
                transition.getAccepted().stream(),
                Stream.concat(
                        transition.getAccepted().stream().filter(Character::isAlphabetic).map(Character::toLowerCase),
                        transition.getAccepted().stream().filter(Character::isAlphabetic).map(Character::toUpperCase)
                )
            ).collect(Collectors.toSet()));
        }
        return transition;
    }

    public Transition parseCharsetValues(RegexParser.CharsetValuesContext ctx) {
        if (ctx.charsetRange() != null) {
            return parseCharsetRange(ctx.charsetRange());
        } else if (ctx.character() != null) {
            return parseCharacter(ctx.character());
        }
        return Transitions.ofString(ctx.getText(), regexConfig);
    }

    @Override
    public Nfa visitExpr(RegexParser.ExprContext ctx) {
        if (ctx.OR() != null) {
            Nfa nfa1 = parseMany1(ctx.expr1());
            Nfa nfa2 = parseMany(ctx.expr());
            return Nfa.union(List.of(nfa1, nfa2));
        }
        return visit(ctx.expr1(0));
    }

    @Override
    public Nfa visitExpr1(RegexParser.Expr1Context ctx) {
        if (ctx.pureExpr() != null) {
            return visit(ctx.pureExpr());
        }
        if (ctx.optionalExpr() != null) {
            return visit(ctx.optionalExpr());
        }
        return visit(ctx.repeatedExpr());
    }

    @Override
    public Nfa visitPureExpr(RegexParser.PureExprContext ctx) {
        if (ctx.charset() != null) {
            return visit(ctx.charset());
        }
        if (ctx.L_PAR() != null) {
            return parseMany(ctx.expr());
        }
        if (ctx.special() != null) {
            return new Nfa(parseSpecial(ctx.special()));
        }
        return new Nfa(parseCharacter(ctx.character()));
    }

    public Transition parseCharacter(RegexParser.CharacterContext ctx) {
        return Transitions.ofString(ctx.getText(), regexConfig);
    }

    public Transition parseSpecial(RegexParser.SpecialContext ctx) {
        return Transitions.ofString(ctx.getText(), regexConfig);
    }

    @Override
    public Nfa visitCharacter(RegexParser.CharacterContext ctx) {
        Transition transition = parseCharacter(ctx);
        return new Nfa(transition);
    }

    private static class Range {
        final private Integer l, r;

        public Range(Integer single) {
            this.l = this.r = single;
        }

        public Range(Integer l, Integer r) {
            if (l == null) {
                l = 0;
            }
            this.l = l;
            this.r = r;
            if (r != null && r < l) {
                throw new ParsingError("Unexpected range parameters");
            }
        }

        public Nfa apply(Nfa nfa) { // TODO: exponential size?
            Nfa cur = nfa.buildRepeated(l);
            if (r == null) {
                nfa.closure();
            } else {
                nfa.makeOptional();
                nfa = nfa.buildRepeated(r - l);
            }
            cur.append(nfa);
            return cur;
        }
    }

    @Override
    public Nfa visitRepeatedExpr(RegexParser.RepeatedExprContext ctx) {
        Nfa nfa = visit(ctx.pureExpr());
        Range range;
        if (ctx.repeatCounter() != null) {
            visit(ctx.repeatCounter());
            if (storage instanceof Range) {
                range = (Range) storage;
                storage = null;
            } else {
                throw new ParsingError("Expected range");
            }
        } else if (ctx.PLUS() != null) {
            range = new Range(1, null);
        } else {
            range = new Range(0, null);
        }
        return range.apply(nfa);
    }

    public int parseNumber(RegexParser.NumberContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    @Override
    public Nfa visitRangeCounter(RegexParser.RangeCounterContext ctx) {
        int l = parseNumber(ctx.number(0));
        int r = parseNumber(ctx.number(1));
        storage = new Range(l, r);
        return null;
    }

    @Override
    public Nfa visitRBorderCounter(RegexParser.RBorderCounterContext ctx) {
        int r = parseNumber(ctx.number());
        storage = new Range(null, r);
        return null;
    }

    @Override
    public Nfa visitLBorderCounter(RegexParser.LBorderCounterContext ctx) {
        int l = parseNumber(ctx.number());
        storage = new Range(l, null);
        return null;
    }

    @Override
    public Nfa visitExactCounter(RegexParser.ExactCounterContext ctx) {
        int x = parseNumber(ctx.number());
        storage = new Range(x);
        return null;
    }

    @Override
    public Nfa visitOptionalExpr(RegexParser.OptionalExprContext ctx) {
        Nfa pure = visit(ctx.pureExpr());
        pure.makeOptional();
        return pure;
    }
}