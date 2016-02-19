/* AstParser.java */
/* Generated By:JavaCC: Do not edit this line. AstParser.java */
package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.javacc;

import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.*;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

public class AstParser implements AstParserConstants {
    public static void main(String[] args) throws ParseException{
        AstParser parser = new AstParser(System.in);
        parser.P();
    }

  final public Statement P() throws ParseException {Statement statement; StatementList statementList;
    statement = S();
    statementList = PPrime();
{if ("" != null) return new StatementList(statement.getCodePosition(), statement, statementList);}
    jj_consume_token(0);
    throw new Error("Missing return statement in function");
  }

  final public StatementList PPrime() throws ParseException {StatementList statementList;
    jj_consume_token(SEMICOLON);
    statementList = X();
{if ("" != null) return statementList;}
    throw new Error("Missing return statement in function");
  }

  final public StatementList X() throws ParseException {StatementList statementList; Statement statement;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PRINT:
    case ID:{
      statement = S();
      statementList = PPrime();
{if ("" != null) return new StatementList(statement.getCodePosition(), statement, statementList);}
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Statement S() throws ParseException {Token token; Expression expression; ExpressionList expressionList;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:{
      token = jj_consume_token(ID);
      jj_consume_token(ASSIGN);
      expression = E();
{if ("" != null) return new AssignmentStatement(CodePosition.createFromToken(token), token.image, expression);}
      break;
      }
    case PRINT:{
      token = jj_consume_token(PRINT);
      jj_consume_token(LPAREN);
      expressionList = L();
      jj_consume_token(RPAREN);
{if ("" != null) return new PrintStatement(CodePosition.createFromToken(token), expressionList);}
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ExpressionList L() throws ParseException {Expression expression; ExpressionList expressionList;
    expression = E();
    expressionList = LPrime();
if (expressionList != null){
            {if ("" != null) return new PairExpressionList(expression.getCodePosition(), expression, expressionList);}
        } else {
            {if ("" != null) return new LastExpressionList(expression.getCodePosition(), expression);}
        }
    throw new Error("Missing return statement in function");
  }

  final public ExpressionList LPrime() throws ParseException {Token token; Expression expression; ExpressionList expressionList;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COMMA:{
      token = jj_consume_token(COMMA);
      expression = E();
      expressionList = LPrime();
if (expressionList != null){
                {if ("" != null) return new PairExpressionList(CodePosition.createFromToken(token), expression, expressionList);}
            } else {
                {if ("" != null) return new LastExpressionList(CodePosition.createFromToken(token), expression);}
            }
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression E() throws ParseException {Token token; Expression leftOperand; Expression rightOperand; Statement statement;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ID:
    case NUM:{
      leftOperand = T();
      rightOperand = EPrime(leftOperand);
{if ("" != null) return rightOperand != null ? rightOperand : leftOperand;}
      break;
      }
    case LPAREN:{
      token = jj_consume_token(LPAREN);
      statement = S();
      jj_consume_token(COMMA);
      leftOperand = E();
      jj_consume_token(RPAREN);
{if ("" != null) return new EseqExpression(CodePosition.createFromToken(token), statement, leftOperand);}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expression EPrime(Expression leftOperand) throws ParseException {Expression rightOperand; Expression ePrimeExpression;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLUS:
    case MINUS:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        jj_consume_token(PLUS);
        rightOperand = T();
        ePrimeExpression = EPrime(rightOperand);
{if ("" != null) return new OperatorExpression(leftOperand.getCodePosition(), leftOperand, Operator.PLUS,
                ePrimeExpression != null ? ePrimeExpression : rightOperand );}
        break;
        }
      case MINUS:{
        jj_consume_token(MINUS);
        rightOperand = T();
        ePrimeExpression = EPrime(rightOperand);
{if ("" != null) return new OperatorExpression(leftOperand.getCodePosition(), leftOperand, Operator.MINUS,
                ePrimeExpression != null ? ePrimeExpression : rightOperand );}
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression T() throws ParseException {Expression leftOperand; Expression rightOperand; Expression operatorExpression;
    leftOperand = F();
    rightOperand = TPrime(leftOperand);
{if ("" != null) return rightOperand == null ? leftOperand : rightOperand;}
    throw new Error("Missing return statement in function");
  }

  final public Expression TPrime(Expression leftOperand) throws ParseException {Expression rightOperand; Expression tPrimeExpression; Token operatorToken;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TIMES:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        operatorToken = jj_consume_token(TIMES);
        rightOperand = F();
        tPrimeExpression = TPrime(rightOperand);
{if ("" != null) return new OperatorExpression(leftOperand.getCodePosition(), leftOperand, Operator.TIMES,
                tPrimeExpression != null ? tPrimeExpression : rightOperand );}
        break;
        }
      case DIV:{
        jj_consume_token(DIV);
        rightOperand = F();
        tPrimeExpression = TPrime(rightOperand);
{if ("" != null) return new OperatorExpression(leftOperand.getCodePosition(), leftOperand, Operator.DIV,
                tPrimeExpression != null ? tPrimeExpression : rightOperand );}
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
{if ("" != null) return null;}
    throw new Error("Missing return statement in function");
  }

  final public Expression F() throws ParseException {Token token;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUM:{
      token = jj_consume_token(NUM);
{if ("" != null) return new NumExpression(CodePosition.createFromToken(token), Integer.parseInt(token.image));}
      break;
      }
    case ID:{
      token = jj_consume_token(ID);
{if ("" != null) return new IdExpression(CodePosition.createFromToken(token), token.image);}
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public AstParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x802,0x802,0x200,0x1880,0xc,0xc,0x30,0x30,0x1800,};
   }

  /** Constructor with InputStream. */
  public AstParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AstParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AstParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AstParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AstParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AstParser(AstParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AstParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[17];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 17; i++) {
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
