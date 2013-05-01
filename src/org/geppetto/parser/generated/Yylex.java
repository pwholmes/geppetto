/* The following code was generated by JFlex 1.4.3 on 5/1/13 11:14 AM */

package org.geppetto.parser.generated;

import java.util.List;
import org.geppetto.parser.generated.Parser;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 5/1/13 11:14 AM from the specification file
 * <tt>C:/Java/workspace/geppetto/src/org/geppetto/parser/lexer.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\2\2\0\1\3\22\0\1\1\1\6\1\11\3\0"+
    "\1\6\1\0\1\6\1\6\1\6\1\6\1\6\1\4\1\10\1\6"+
    "\12\7\1\0\1\6\1\6\1\6\1\5\2\0\32\35\3\0\1\6"+
    "\2\0\1\16\1\12\1\27\1\21\1\15\1\25\1\31\1\30\1\23"+
    "\2\35\1\14\1\35\1\17\1\13\1\32\1\35\1\26\1\20\1\22"+
    "\1\33\1\35\1\34\1\35\1\24\1\35\1\6\1\6\1\6\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\3\2\2\3\1\4\1\1\13\5\1\6"+
    "\2\0\1\7\6\5\1\10\7\5\1\11\2\5\1\12"+
    "\3\5\1\13\1\5\1\14\10\5\1\15\2\5\1\16"+
    "\4\5\1\17\7\5\1\20\1\5\1\21\1\22\2\5"+
    "\1\23\1\24\1\5\1\25\1\26\1\5\1\27\1\5"+
    "\1\30\1\31\1\5\1\32";

  private static int [] zzUnpackAction() {
    int [] result = new int[90];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\36\0\74\0\36\0\132\0\170\0\36\0\226"+
    "\0\264\0\322\0\360\0\u010e\0\u012c\0\u014a\0\u0168\0\u0186"+
    "\0\u01a4\0\u01c2\0\u01e0\0\u01fe\0\36\0\u021c\0\264\0\36"+
    "\0\u023a\0\u0258\0\u0276\0\u0294\0\u02b2\0\u02d0\0\360\0\u02ee"+
    "\0\u030c\0\u032a\0\u0348\0\u0366\0\u0384\0\u03a2\0\u021c\0\u03c0"+
    "\0\u03de\0\360\0\u03fc\0\u041a\0\u0438\0\360\0\u0456\0\u0474"+
    "\0\u0492\0\u04b0\0\u04ce\0\u04ec\0\u050a\0\u0528\0\u0546\0\u0564"+
    "\0\360\0\u0582\0\u05a0\0\360\0\u05be\0\u05dc\0\u05fa\0\u0618"+
    "\0\360\0\u0636\0\u0654\0\u0672\0\u0690\0\u06ae\0\u06cc\0\u06ea"+
    "\0\360\0\u0708\0\360\0\360\0\u0726\0\u0744\0\360\0\360"+
    "\0\u0762\0\360\0\360\0\u0780\0\360\0\u079e\0\360\0\360"+
    "\0\u07bc\0\360";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[90];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\2\7\1\10\1\2"+
    "\1\11\1\12\2\13\1\14\2\13\1\15\1\13\1\16"+
    "\1\17\1\13\1\20\1\21\2\13\1\22\1\23\1\13"+
    "\1\24\1\13\37\0\1\3\36\0\1\4\40\0\1\25"+
    "\37\0\1\10\1\26\25\0\11\27\1\30\24\27\7\0"+
    "\1\13\2\0\1\13\1\31\22\13\7\0\1\13\2\0"+
    "\24\13\7\0\1\13\2\0\2\13\1\32\2\13\1\33"+
    "\16\13\7\0\1\13\2\0\10\13\1\34\13\13\7\0"+
    "\1\13\2\0\14\13\1\35\7\13\7\0\1\13\2\0"+
    "\5\13\1\36\5\13\1\37\10\13\7\0\1\13\2\0"+
    "\1\13\1\40\1\41\1\13\1\42\17\13\7\0\1\13"+
    "\2\0\21\13\1\43\2\13\7\0\1\13\2\0\2\13"+
    "\1\44\21\13\7\0\1\13\2\0\14\13\1\45\7\13"+
    "\7\0\1\13\2\0\16\13\1\46\5\13\7\0\1\47"+
    "\35\0\1\13\2\0\1\13\1\50\22\13\7\0\1\13"+
    "\2\0\6\13\1\51\15\13\7\0\1\13\2\0\7\13"+
    "\1\52\1\53\13\13\7\0\1\13\2\0\14\13\1\54"+
    "\7\13\7\0\1\13\2\0\21\13\1\55\2\13\7\0"+
    "\1\13\2\0\10\13\1\56\7\13\1\57\3\13\7\0"+
    "\1\13\2\0\14\13\1\60\7\13\7\0\1\13\2\0"+
    "\1\13\1\61\22\13\7\0\1\13\2\0\2\13\1\62"+
    "\21\13\7\0\1\13\2\0\2\13\1\63\21\13\7\0"+
    "\1\13\2\0\1\13\1\64\22\13\7\0\1\13\2\0"+
    "\1\13\1\65\7\13\1\66\12\13\7\0\1\13\2\0"+
    "\11\13\1\67\12\13\7\0\1\13\2\0\2\13\1\70"+
    "\21\13\7\0\1\13\2\0\3\13\1\71\20\13\7\0"+
    "\1\13\2\0\11\13\1\72\12\13\7\0\1\13\2\0"+
    "\11\13\1\73\12\13\7\0\1\13\2\0\3\13\1\74"+
    "\20\13\7\0\1\13\2\0\21\13\1\75\2\13\7\0"+
    "\1\13\2\0\3\13\1\76\20\13\7\0\1\13\2\0"+
    "\4\13\1\77\17\13\7\0\1\13\2\0\6\13\1\100"+
    "\15\13\7\0\1\13\2\0\3\13\1\101\20\13\7\0"+
    "\1\13\2\0\1\102\23\13\7\0\1\13\2\0\20\13"+
    "\1\103\3\13\7\0\1\13\2\0\5\13\1\104\16\13"+
    "\7\0\1\13\2\0\2\13\1\105\21\13\7\0\1\13"+
    "\2\0\3\13\1\106\20\13\7\0\1\13\2\0\10\13"+
    "\1\107\13\13\7\0\1\13\2\0\5\13\1\110\16\13"+
    "\7\0\1\13\2\0\10\13\1\111\13\13\7\0\1\13"+
    "\2\0\4\13\1\112\17\13\7\0\1\13\2\0\10\13"+
    "\1\113\13\13\7\0\1\13\2\0\3\13\1\114\20\13"+
    "\7\0\1\13\2\0\4\13\1\115\17\13\7\0\1\13"+
    "\2\0\3\13\1\116\20\13\7\0\1\13\2\0\10\13"+
    "\1\117\13\13\7\0\1\13\2\0\3\13\1\120\20\13"+
    "\7\0\1\13\2\0\4\13\1\121\17\13\7\0\1\13"+
    "\2\0\12\13\1\122\11\13\7\0\1\13\2\0\17\13"+
    "\1\123\4\13\7\0\1\13\2\0\15\13\1\124\6\13"+
    "\7\0\1\13\2\0\2\13\1\125\21\13\7\0\1\13"+
    "\2\0\14\13\1\126\7\13\7\0\1\13\2\0\5\13"+
    "\1\127\16\13\7\0\1\13\2\0\16\13\1\130\5\13"+
    "\7\0\1\13\2\0\10\13\1\131\13\13\7\0\1\13"+
    "\2\0\12\13\1\132\11\13";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2010];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\2\1\1\11\15\1\1\11"+
    "\2\0\1\11\102\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[90];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;
  private List<String> symbolTable;

  public Yylex(java.io.Reader r, Parser yyparser, List<String> symbolTable) {
    this(r);
    this.yyparser = yyparser;
    this.symbolTable = symbolTable;
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 120) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 17: 
          { return Parser.FLOAT;
          }
        case 27: break;
        case 5: 
          { symbolTable.add(yytext()); yyparser.yylval = new ParserVal(symbolTable.indexOf(yytext())); return Parser.IDENTIFIER;
          }
        case 28: break;
        case 22: 
          { return Parser.STRING;
          }
        case 29: break;
        case 8: 
          { return Parser.IF;
          }
        case 30: break;
        case 1: 
          { System.err.println("Error: unexpected character '" + yytext() + "'"); return -1;
          }
        case 31: break;
        case 6: 
          { return Parser.INFERS;
          }
        case 32: break;
        case 3: 
          { return (int) yycharat(0);
          }
        case 33: break;
        case 25: 
          { return Parser.FOREACH;
          }
        case 34: break;
        case 19: 
          { return Parser.PRINT;
          }
        case 35: break;
        case 12: 
          { return Parser.FOR;
          }
        case 36: break;
        case 26: 
          { return Parser.PROPERTY;
          }
        case 37: break;
        case 13: 
          { return Parser.ELSE;
          }
        case 38: break;
        case 9: 
          { yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.FLOAT_LITERAL;
          }
        case 39: break;
        case 18: 
          { return Parser.FALSE;
          }
        case 40: break;
        case 14: 
          { return Parser.TRUE;
          }
        case 41: break;
        case 7: 
          { symbolTable.add(yytext()); yyparser.yylval.ival = symbolTable.indexOf(yytext()); return Parser.STRING_LITERAL;
          }
        case 42: break;
        case 24: 
          { return Parser.BOOLEAN;
          }
        case 43: break;
        case 20: 
          { return Parser.WHILE;
          }
        case 44: break;
        case 16: 
          { return Parser.INPUT;
          }
        case 45: break;
        case 10: 
          { return Parser.END;
          }
        case 46: break;
        case 21: 
          { return Parser.ENTITY;
          }
        case 47: break;
        case 15: 
          { return Parser.RULE;
          }
        case 48: break;
        case 11: 
          { return Parser.INT;
          }
        case 49: break;
        case 23: 
          { return Parser.GLOBAL;
          }
        case 50: break;
        case 4: 
          { yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); return Parser.INTEGER_LITERAL;
          }
        case 51: break;
        case 2: 
          { 
          }
        case 52: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
