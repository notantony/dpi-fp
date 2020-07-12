package parsing;

import antlr_generated.RegexBaseVisitor;
import antlr_generated.RegexParser;
import automaton.transition.SingleElementTransition;
import automaton.transition.Transition;
import automaton.transition.Transitions;

public class RegexVisitorImpl<T> extends RegexBaseVisitor<T> {

    @Override
    public T visitStart(RegexParser.StartContext ctx) {
        return super.visitStart(ctx);
    }

    @Override
    public T visitParams(RegexParser.ParamsContext ctx) {
        return super.visitParams(ctx);
    }

    @Override
    public T visitCharset(RegexParser.CharsetContext ctx) {
        return super.visitCharset(ctx);
    }

    @Override
    public T visitCharset_range(RegexParser.Charset_rangeContext ctx) {
        return super.visitCharset_range(ctx);
    }

    @Override
    public T visitCharset_values(RegexParser.Charset_valuesContext ctx) {
        return super.visitCharset_values(ctx);
    }

    @Override
    public T visitExpr(RegexParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public T visitPure_expr(RegexParser.Pure_exprContext ctx) {
        return super.visitPure_expr(ctx);
    }

    @Override
    public T visitCharacter(RegexParser.CharacterContext ctx) {
        Transitions.ofString(ctx.getText());
        return super.visitCharacter(ctx);
    }

    @Override
    public T visitRepeated_expr(RegexParser.Repeated_exprContext ctx) {
        return super.visitRepeated_expr(ctx);
    }

    @Override
    public T visitNumber(RegexParser.NumberContext ctx) {
        return super.visitNumber(ctx);
    }

    @Override
    public T visitRepeat_counter(RegexParser.Repeat_counterContext ctx) {
        return super.visitRepeat_counter(ctx);
    }

    @Override
    public T visitOptional_expr(RegexParser.Optional_exprContext ctx) {
        return super.visitOptional_expr(ctx);
    }
}
