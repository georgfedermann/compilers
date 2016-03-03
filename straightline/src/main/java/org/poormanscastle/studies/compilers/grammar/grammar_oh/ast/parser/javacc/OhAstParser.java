/* OhAstParser.java */
/* Generated By:JavaCC: Do not edit this line. OhAstParser.java */
package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.parser.javacc;

import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.*;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

public class OhAstParser implements OhAstParserConstants {
    public static void main(String[] args) throws ParseException{
        OhAstParser parser = new OhAstParser(System.in);
        parser.P();
    }

  final public Program P() throws ParseException {StatementList statementList;
    statementList = PPrime();
    jj_consume_token(0);
{if ("" != null) return new ProgramImpl(statementList == null ? CodePosition.createZeroPosition() : statementList.getCodePosition(),
            statementList);}
    throw new Error("Missing return statement in function");
  }

  final public StatementList PPrime() throws ParseException {Statement statement; StatementList statementList;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PRINT:
    case TYPE:
    case IF:
    case SEMICOLON:
    case LBRACE:
    case ID:{
      statement = S();
      jj_consume_token(SEMICOLON);
      statementList = PPrime();
{if ("" != null) return statementList == null ?
                new LastStatementList(statement) :
                new PairStatementList(statement, statementList);}
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

/* TODO check this out: why does the conditional statement go missing in the generated parser if statement = CS()
 comes after statement = B() ? */
  final public Statement S() throws ParseException {Statement statement;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TYPE:{
      statement = DS();
{if ("" != null) return statement;}
      break;
      }
    case ID:{
      statement = AS();
{if ("" != null) return statement;}
      break;
      }
    case PRINT:{
      statement = PS();
{if ("" != null) return statement;}
      break;
      }
    case IF:{
      statement = CS();
{if ("" != null) return statement;}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      statement = B();
{if ("" != null) return statement;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Statement B() throws ParseException {StatementList statementList;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LBRACE:{
      jj_consume_token(LBRACE);
      statementList = BPrime();
      jj_consume_token(RBRACE);
{if ("" != null) return new Block(statementList);}
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    throw new Error("Missing return statement in function");
  }

  final public StatementList BPrime() throws ParseException {Statement statement; StatementList statementList;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PRINT:
    case TYPE:
    case IF:
    case SEMICOLON:
    case LBRACE:
    case ID:{
      statement = S();
      jj_consume_token(SEMICOLON);
      statementList = BPrime();
{if ("" != null) return statementList == null ?
                new LastStatementList(statement) :
                new PairStatementList(statement, statementList);}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Statement CS() throws ParseException {Expression condition; Statement thenStatement; Statement elseStatement;
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    condition = E();
    jj_consume_token(RPAREN);
    thenStatement = S();
    elseStatement = CSPrime();
{if ("" != null) return new ConditionalStatement(condition, thenStatement, elseStatement);}
    throw new Error("Missing return statement in function");
  }

  final public Statement CSPrime() throws ParseException {Statement statement;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      statement = S();
{if ("" != null) return statement;}
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Statement DS() throws ParseException {Token typeToken, idToken; Expression expression;
    typeToken = jj_consume_token(TYPE);
    idToken = jj_consume_token(ID);
    expression = DSPrime();
{if ("" != null) return expression == null ?
            new DeclarationStatement(CodePosition.createFromToken(typeToken), typeToken.image, idToken.image) :
            new DeclarationStatement(typeToken.image, idToken.image, expression);}
    throw new Error("Missing return statement in function");
  }

  final public Expression DSPrime() throws ParseException {Expression expression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LET:{
      jj_consume_token(LET);
      expression = E();
{if ("" != null) return expression;}
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Statement AS() throws ParseException {Statement statement; Token idToken; Expression expression;
    idToken = jj_consume_token(ID);
    jj_consume_token(LET);
    expression = E();
{if ("" != null) return new AssignmentStatement(idToken.image, expression);}
    throw new Error("Missing return statement in function");
  }

  final public Statement PS() throws ParseException {ExpressionList expressionList;
    jj_consume_token(PRINT);
    jj_consume_token(LPAREN);
    expressionList = EL();
    jj_consume_token(RPAREN);
{if ("" != null) return new PrintStatement(expressionList);}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionList EL() throws ParseException {Expression expression; ExpressionList expressionList;
    expression = E();
    expressionList = ELPrime();
{if ("" != null) return expressionList == null ? new LastExpressionList(expression.getCodePosition(), expression) :
            new PairExpressionList(expression.getCodePosition(), expression, expressionList);}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionList ELPrime() throws ParseException {Expression expression; ExpressionList expressionList;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COMMA:{
      jj_consume_token(COMMA);
      expression = E();
      expressionList = ELPrime();
{if ("" != null) return expressionList == null ? new LastExpressionList(expression.getCodePosition(), expression) :
            new PairExpressionList(expression, expressionList);}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression E() throws ParseException {Expression lhs, rhs;
    lhs = T8();
    rhs = EPrime(lhs);
{if ("" != null) return rhs == null ? lhs : rhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression EPrime(Expression lhs) throws ParseException {Expression rhs; Expression ePrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OR:{
      jj_consume_token(OR);
      rhs = T8();
      ePrimeExpression = EPrime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.OR,
                ePrimeExpression != null ? ePrimeExpression : rhs );}
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T8() throws ParseException {Expression lhs, rhs;
    lhs = T7();
    rhs = T8Prime(lhs);
{if ("" != null) return rhs == null ? lhs : rhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T8Prime(Expression lhs) throws ParseException {Expression rhs, t8PrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:{
      jj_consume_token(AND);
      rhs = T7();
      t8PrimeExpression = T8Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.AND,
                t8PrimeExpression != null ? t8PrimeExpression : rhs );}
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T7() throws ParseException {Expression lhs, rhs;
    lhs = T6();
    rhs = T7Prime(lhs);
{if ("" != null) return rhs != null ? rhs : lhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T7Prime(Expression lhs) throws ParseException {Expression rhs, t7PrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case XOR:{
      jj_consume_token(XOR);
      rhs = T6();
      t7PrimeExpression = T7Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.XOR,
                t7PrimeExpression != null ? t7PrimeExpression : rhs );}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T6() throws ParseException {Expression lhs, rhs;
    lhs = T5();
    rhs = T6Prime(lhs);
{if ("" != null) return rhs != null ? rhs : lhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T6Prime(Expression lhs) throws ParseException {Expression rhs, t6PrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:{
      jj_consume_token(EQ);
      rhs = T5();
      t6PrimeExpression = T6Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.EQ,
                t6PrimeExpression != null ? t6PrimeExpression : rhs );}
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T5() throws ParseException {Expression lhs, rhs;
    lhs = T4();
    rhs = T5Prime(lhs);
{if ("" != null) return rhs != null ? rhs : lhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T5Prime(Expression lhs) throws ParseException {Expression rhs, t5PrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LT:
    case LTE:
    case GT:
    case GTE:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LT:{
        jj_consume_token(LT);
        rhs = T4();
        t5PrimeExpression = T5Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.LT,
                t5PrimeExpression != null ? t5PrimeExpression : rhs );}
        break;
        }
      case LTE:{
        jj_consume_token(LTE);
        rhs = T4();
        t5PrimeExpression = T5Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.LTE,
                t5PrimeExpression != null ? t5PrimeExpression : rhs );}
        break;
        }
      case GT:{
        jj_consume_token(GT);
        rhs = T4();
        t5PrimeExpression = T5Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.GT,
                t5PrimeExpression != null ? t5PrimeExpression : rhs );}
        break;
        }
      case GTE:{
        jj_consume_token(GTE);
        rhs = T4();
        t5PrimeExpression = T5Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.GTE,
                t5PrimeExpression != null ? t5PrimeExpression : rhs );}
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T4() throws ParseException {Expression lhs, rhs;
    lhs = T3();
    rhs = T4Prime(lhs);
{if ("" != null) return rhs != null ? rhs : lhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T4Prime(Expression lhs) throws ParseException {Expression rhs, t4PrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLUS:
    case MINUS:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        jj_consume_token(PLUS);
        rhs = T3();
        t4PrimeExpression = T4Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.PLUS,
                t4PrimeExpression != null ? t4PrimeExpression : rhs );}
        break;
        }
      case MINUS:{
        jj_consume_token(MINUS);
        rhs = T3();
        t4PrimeExpression = T4Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.MINUS,
                t4PrimeExpression != null ? t4PrimeExpression : rhs );}
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T3() throws ParseException {Expression lhs, rhs;
    lhs = T2();
    rhs = T3Prime(lhs);
{if ("" != null) return rhs != null ? rhs : lhs;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T3Prime(Expression lhs) throws ParseException {Expression rhs, t3PrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TIMES:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        jj_consume_token(TIMES);
        rhs = T2();
        t3PrimeExpression = T3Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.TIMES,
                t3PrimeExpression != null ? t3PrimeExpression : rhs );}
        break;
        }
      case DIV:{
        jj_consume_token(DIV);
        rhs = T2();
        t3PrimeExpression = T3Prime(rhs);
{if ("" != null) return new BinaryOperatorExpression(lhs, BinaryOperator.DIV,
                t3PrimeExpression != null ? t3PrimeExpression : rhs );}
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T2() throws ParseException {Expression f;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case BOOL:
    case NUM:
    case DEC:
    case TEXT:
    case ID:{
      f = F();
{if ("" != null) return f;}
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
      f = F();
{if ("" != null) return new UnaryOperatorExpression(UnaryOperator.NOT, f);}
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expression F() throws ParseException {Token valueToken; Expression expression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      valueToken = jj_consume_token(ID);
{if ("" != null) return new IdExpression(CodePosition.createFromToken(valueToken), valueToken.image);}
      break;
      }
    case NUM:{
      valueToken = jj_consume_token(NUM);
{if ("" != null) return new NumExpression(CodePosition.createFromToken(valueToken), Integer.parseInt(valueToken.image));}
      break;
      }
    case DEC:{
      valueToken = jj_consume_token(DEC);
{if ("" != null) return new DecimalExpression(CodePosition.createFromToken(valueToken), Double.parseDouble(valueToken.image));}
      break;
      }
    case BOOL:{
      valueToken = jj_consume_token(BOOL);
{if ("" != null) return new BooleanExpression(CodePosition.createFromToken(valueToken), Boolean.parseBoolean(valueToken.image));}
      break;
      }
    case TEXT:{
      valueToken = jj_consume_token(TEXT);
{if ("" != null) return new TextExpression(CodePosition.createFromToken(valueToken), valueToken.image);}
      break;
      }
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public OhAstParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x43280002,0x40280002,0x2000000,0x43280002,0x400000,0x40000,0x800000,0x20000,0x10000,0x8000,0x2000,0x1e00,0x1e00,0x180,0x180,0x60,0x60,0x78100010,0x78100000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public OhAstParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public OhAstParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new OhAstParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public OhAstParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new OhAstParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public OhAstParser(OhAstParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(OhAstParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[38];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 38; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
