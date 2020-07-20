// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RegexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RegexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RegexParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(RegexParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(RegexParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#charset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharset(RegexParser.CharsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#charsetRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharsetRange(RegexParser.CharsetRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#charsetValues}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharsetValues(RegexParser.CharsetValuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RegexParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr1(RegexParser.Expr1Context ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#pureExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPureExpr(RegexParser.PureExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(RegexParser.CharacterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#special}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecial(RegexParser.SpecialContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#repeatedExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatedExpr(RegexParser.RepeatedExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(RegexParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rangeCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeCounter(RegexParser.RangeCounterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lBorderCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLBorderCounter(RegexParser.LBorderCounterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rBorderCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRBorderCounter(RegexParser.RBorderCounterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exactCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExactCounter(RegexParser.ExactCounterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#optionalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionalExpr(RegexParser.OptionalExprContext ctx);
}