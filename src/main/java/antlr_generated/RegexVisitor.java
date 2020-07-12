// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr_generated;
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
	 * Visit a parse tree produced by {@link RegexParser#charset_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharset_range(RegexParser.Charset_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#charset_values}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharset_values(RegexParser.Charset_valuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RegexParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#pure_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPure_expr(RegexParser.Pure_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#character}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter(RegexParser.CharacterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#repeated_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeated_expr(RegexParser.Repeated_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(RegexParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#repeat_counter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat_counter(RegexParser.Repeat_counterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RegexParser#optional_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptional_expr(RegexParser.Optional_exprContext ctx);
}