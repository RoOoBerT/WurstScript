package de.peeeq.wurstscript.parser;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.peeeq.wurstscript.WLogger;
import de.peeeq.wurstscript.ast.Ast;
import de.peeeq.wurstscript.ast.WPos;
import de.peeeq.wurstscript.attributes.CompileError;
import de.peeeq.wurstscript.gui.WurstGui;
import de.peeeq.wurstscript.utils.NotNullList;
import de.peeeq.wurstscript.utils.Utils;

/**
 * This parser extends the parser class generated by java-cup and adds some
 * stuff like error handling
 * 
 */
public class ExtendedParser extends parser {

	private List<CompileError> errors = new NotNullList<CompileError>();
	private WurstGui gui;

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public Symbol parse() throws Exception {
		Symbol sym = super.parse();
		// Example for adding standard library:
		// CompilationUnit root = (CompilationUnit) sym.value;
		// TypeDecls typeDecls = root.typeDecls();
		// SourcePosition pos = SourcePosition(0, 0);
		// typeDecls = typeDecls.add(
		// // class IO
		// ClassDecl(
		// pos ,
		// Identifier(pos, "IO"),
		// IdentifierNone(),
		// ClassBodyDecls(
		// // static void write(int i)
		// MethodDecl(pos, Modifiers(Static(pos)), VoidT(pos), Identifier(pos,
		// "write"),
		// FormalParameters(FormalParameter(pos, Int(pos), Identifier(pos,
		// "i"))), InternalMethodBody()),
		// // static int read()
		// MethodDecl(pos, Modifiers(Static(pos)), Int(pos), Identifier(pos,
		// "read"),
		// FormalParameters(), InternalMethodBody())
		// )
		// ));
		// root = CompilationUnit(root.sourcePos(), typeDecls);
		// sym.value = root;
		return sym;
	}

	public ExtendedParser(Scanner scanner, WurstGui gui) {
		super(scanner);
		this.gui = gui;
	}

	public int getErrorCount() {
		return errors.size();
	}

	/**
	 * returns the position of a Symbol as string
	 */
	private String pos(Symbol s) {
		return "line " + (s.left + 1) + ", column " + s.right;
	}

	/**
	 * uses reflection to get the name of a Symbol
	 */
	private String translateSym(int index) {
		try {
			Class<TokenType> symbolClass = TokenType.class;

			for (Field f : symbolClass.getDeclaredFields()) {
				if (f.getInt(null) == index) {
					return translateSymbolName(f.getName());
				}
			}
		} catch (Throwable e) {
			WLogger.severe(e);
		}
		return "Symbol#" + index;
	}

	private String translateSymbolName(String name) {
		Map<String, String> translations = Maps.newHashMap();
		translations.put("IDENTIFIER", "name");
		translations.put("GT", "'>'");
		translations.put("NOTEQ", "'!='");
		translations.put("DIV_INT", "'div'");
		translations.put("RBRACK", "'}'");
		translations.put("COMMA", "','");
		translations.put("SEMICOLON", "';'");
		translations.put("LBRACK", "'{'");
		translations.put("LT", "'<'");
		translations.put("DIV_REAL", "'/'");
		translations.put("INTEGER_LITERAL", "integer number");
		translations.put("EQ", "'='");
		translations.put("PLUS", "'+'");
		translations.put("LPAR", "'('");
		translations.put("REAL_LITERAL", "real number");
		translations.put("RSQUARE", "']'");
		translations.put("MOD_REAL", "'%'");
		translations.put("MULT", "'*'");
		translations.put("GTEQ", "'>='");
		translations.put("DOT", "'.'");
		translations.put("STRING_LITERAL", "string");
		translations.put("EQEQ", "'=='");
		translations.put("EOF", "end of file");
		translations.put("LSQUARE", "'['");
		translations.put("RPAR", "')'");
		translations.put("MINUS", "'-'");
		translations.put("LTEQ", "'<='");
		translations.put("CASTTO", "'cast_to'");
		translations.put("MOD_INT", "'mod'");
		translations.put("NL", "newline");
		translations.put("UMINUS", "'-'");
		if (translations.containsKey(name)) {
			return translations.get(name);
		} else {
			// just assume this is a keyword 
			return "'" + name.toLowerCase() + "'";
		}
	}

	private String symbolToString(Symbol s) {
		return translateSym(s.sym) + (s.value != null ? " " + s.value : "");
	}

	/**
	 * 
	 */
	@Override
	public void syntax_error(Symbol s) {

		String msg;
		if (s.sym == TokenType.error) {
			msg = "Lexical error: unexpected symbol <" + s.value + "> ";
		} else {
			msg = "Grammatical error: unexpected " + symbolToString(s);
		}

		// get current parse state:
		int parseState = ((Symbol) stack.peek()).parse_state;

		msg += "	expected: ";
		// get possible actions from action table and print them
		short[] possibleActions = this.action_table()[parseState];
		List<String> expectedSymbols = Lists.newLinkedList();
		for (int j = 0; j < possibleActions.length; j += 2) {
			if (possibleActions[j] >= 0) {
				expectedSymbols.add(translateSym(possibleActions[j]));
			}
		}
		groupEntries(expectedSymbols);		
		msg += Utils.join(expectedSymbols, ", ");  
		//msg += "\nstate = " + parseState;
		
		
		WPos source = Ast.WPos(filename, s.left, s.right);
		CompileError err = new CompileError(source, msg);
		errors.add(err);
		gui.sendError(err);
		// throw err;
	}

	private void groupEntries(List<String> expectedSymbols) {
		Map<String, Set<String>> groups = Maps.newHashMap();
		groups.put("operator", Sets.newHashSet(
				"'=='", "'>='", "'<='", "'!='", "'>'", "'<'", "'and'", "'or'", "'+'", "'-'", "'*'", 
				"'/'", "'div'", "'%'", "'mod'", "'.'", "'cast_to'"));
		groups.put("expression", Sets.newHashSet(
				translateSym(TokenType.IDENTIFIER), 
				translateSym(TokenType.INTEGER_LITERAL),
				translateSym(TokenType.REAL_LITERAL),
				translateSym(TokenType.STRING_LITERAL),
				translateSym(TokenType.TRUE),
				translateSym(TokenType.FALSE),
				translateSym(TokenType.NOT),
				translateSym(TokenType.MINUS),
				translateSym(TokenType.LPAR),
				translateSym(TokenType.FUNCTION),
				translateSym(TokenType.NEW),
				translateSym(TokenType.NULL),
				translateSym(TokenType.THIS)
				));
		
		Set<String> matchingGroups = Sets.newHashSet();
		for (String groupName : groups.keySet()) {
			Set<String> group = groups.get(groupName);
			if (expectedSymbols.containsAll(group)) {
				matchingGroups.add(groupName);
			}
		}
		
		for (String groupName : matchingGroups) {
			expectedSymbols.removeAll(groups.get(groupName));
			expectedSymbols.add(0, groupName);
		}
		
	}

	@Override
	public void unrecovered_syntax_error(Symbol s) {
		WPos source = Ast.WPos(filename, s.left, s.right);
		throw new CompileError(source, "Could not continue to parse file ...");
	}

}
