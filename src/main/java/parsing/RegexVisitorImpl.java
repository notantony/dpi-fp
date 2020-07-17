package parsing;

import antlr_generated.RegexBaseVisitor;
import antlr_generated.RegexParser;
import automaton.nfa.Nfa;
import automaton.nfa.State;
import automaton.transition.Transition;
import automaton.transition.Transitions;

import java.util.Collection;
import java.util.stream.Collectors;

public class RegexVisitorImpl extends RegexBaseVisitor<Nfa> {
    private RegexConfig regexConfig;
    private Object storage;

    private Nfa parseMany(Collection<RegexParser.ExprContext> expressions) {
        return Nfa.concat(expressions.stream().map(this::visitExpr).collect(Collectors.toList()));
    }

    @Override
    public Nfa visitStart(RegexParser.StartContext ctx) {
        regexConfig = new RegexConfig(ctx.params().getText());
        return parseMany(ctx.expr());
    }


    @Override
    public Nfa visitParams(RegexParser.ParamsContext ctx) {
        return super.visitParams(ctx);
    }

    @Override
    public Nfa visitCharset(RegexParser.CharsetContext ctx) {
        return super.visitCharset(ctx);
    }

    @Override
    public Nfa visitCharsetRange(RegexParser.CharsetRangeContext ctx) {
        return super.visitCharsetRange(ctx);
    }

    @Override
    public Nfa visitCharsetValues(RegexParser.CharsetValuesContext ctx) {
        return super.visitCharsetValues(ctx);
    }

    @Override
    public Nfa visitExpr(RegexParser.ExprContext ctx) {
        if (ctx.pureExpr() != null) {
            return visit(ctx.pureExpr());
        }
        if (ctx.optionalExpr() != null) {
            throw new RuntimeException();
        }
        if (ctx.repeatedExpr() != null) { // TODO
            return visit(ctx.repeatedExpr());
        }
        throw new RuntimeException(); // OR
//        visit()
    }

    @Override
    public Nfa visitPureExpr(RegexParser.PureExprContext ctx) {
        if (ctx.charset() != null) {
            throw new RuntimeException();
        }
        if (ctx.L_PAR() != null) {
            return parseMany(ctx.expr());
        }

        return super.visitPureExpr(ctx);
    }

    @Override
    public Nfa visitCharacter(RegexParser.CharacterContext ctx) {
        Transition transition = Transitions.ofString(ctx.getText(), regexConfig);
        Nfa nfa = new Nfa(false);
        State finish = new State();
        nfa.getStart().addEdge(transition, finish);
        nfa.getTerminals().add(finish);
        return nfa;
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
                throw new IllegalArgumentException("Unexpected range parameters");
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
        if (ctx.repeatCounter() != null) {
            visit(ctx.repeatCounter());
            if (storage instanceof Range) {
                nfa = ((Range) storage).apply(nfa);
            } else {
                throw new IllegalArgumentException("Expected range");
            }
        } else {
            throw new RuntimeException();
        }
        return nfa;
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
        return super.visitOptionalExpr(ctx);
    }
}