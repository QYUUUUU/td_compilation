
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package generated.fr.ul.miashs.expression;

import java_cup.runtime.*;
import fr.ul.miashs.compil.arbre.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class ParserCup extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return Sym.class;
}

  /** Default constructor. */
  @Deprecated
  public ParserCup() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public ParserCup(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public ParserCup(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\015\000\002\002\004\000\002\002\003\000\002\003" +
    "\003\000\002\003\004\000\002\004\004\000\002\004\006" +
    "\000\002\005\005\000\002\005\005\000\002\005\003\000" +
    "\002\005\003\000\002\006\004\000\002\006\004\000\002" +
    "\006\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\027\000\010\024\012\025\010\026\011\001\002\000" +
    "\012\002\uffff\024\uffff\025\uffff\026\uffff\001\002\000\004" +
    "\002\031\001\002\000\012\002\000\024\012\025\010\026" +
    "\011\001\002\000\006\015\017\016\016\001\002\000\004" +
    "\005\015\001\002\000\004\005\014\001\002\000\004\005" +
    "\013\001\002\000\006\015\ufff7\016\ufff7\001\002\000\006" +
    "\015\ufff5\016\ufff5\001\002\000\006\015\ufff6\016\ufff6\001" +
    "\002\000\012\002\ufffd\024\ufffd\025\ufffd\026\ufffd\001\002" +
    "\000\006\004\021\005\020\001\002\000\010\007\ufff8\010" +
    "\ufff8\016\ufff8\001\002\000\010\007\ufff9\010\ufff9\016\ufff9" +
    "\001\002\000\010\007\024\010\025\016\023\001\002\000" +
    "\012\002\ufffc\024\ufffc\025\ufffc\026\ufffc\001\002\000\006" +
    "\004\021\005\020\001\002\000\006\004\021\005\020\001" +
    "\002\000\010\007\ufffa\010\ufffa\016\ufffa\001\002\000\010" +
    "\007\ufffb\010\ufffb\016\ufffb\001\002\000\012\002\ufffe\024" +
    "\ufffe\025\ufffe\026\ufffe\001\002\000\004\002\001\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\027\000\012\002\004\003\005\004\003\006\006\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\004\027" +
    "\006\006\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\005" +
    "\021\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\005\026\001\001\000" +
    "\004\005\025\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$ParserCup$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$ParserCup$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$ParserCup$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}






/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$ParserCup$actions {
  private final ParserCup parser;

  /** Constructor */
  CUP$ParserCup$actions(ParserCup parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$ParserCup$do_action_part00000000(
    int                        CUP$ParserCup$act_num,
    java_cup.runtime.lr_parser CUP$ParserCup$parser,
    java.util.Stack            CUP$ParserCup$stack,
    int                        CUP$ParserCup$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$ParserCup$result;

      /* select the action based on the action number */
      switch (CUP$ParserCup$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= programme EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).right;
		Noeud start_val = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).value;
		RESULT = start_val;
              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$ParserCup$parser.done_parsing();
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // programme ::= instructionList 
            {
              Noeud RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		Bloc i = (Bloc)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    Noeud Programme = new Prog();
    Programme.ajouterUnFils(i);
    RESULT = Programme;

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("programme",0, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // instructionList ::= instruction 
            {
              Bloc RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		Noeud i = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    Bloc b = new Bloc();
    b.ajouterUnFils(i);
    RESULT = b;

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("instructionList",1, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // instructionList ::= instructionList instruction 
            {
              Bloc RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).right;
		Bloc l = (Bloc)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).value;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		Noeud i = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
        l.ajouterUnFils(i);
        RESULT = l;

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("instructionList",1, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // instruction ::= variable POINT_VIRGULE 
            {
              Noeud RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).right;
		Noeud v = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).value;
		
                RESULT = new Idf(v);

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("instruction",2, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // instruction ::= variable ASSIGNER expression POINT_VIRGULE 
            {
              Noeud RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-3)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-3)).right;
		Noeud v = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).right;
		Noeud e = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)).value;
		
              Affectation a = new Affectation();
              a.setFilsGauche(v);
              a.setFilsDroit(e);
              RESULT = a;

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("instruction",2, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-3)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // expression ::= expression PLUS expression 
            {
              Noeud RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)).right;
		Noeud e1 = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		Noeud e2 = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    Plus p = new Plus();
    p.setFilsGauche(e1);
    p.setFilsDroit(e2);
    RESULT = p;

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // expression ::= expression MOINS expression 
            {
              Noeud RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)).right;
		Noeud e1 = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		Noeud e2 = (Noeud)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    Moins m = new Moins();
    m.setFilsGauche(e1);
    m.setFilsDroit(e2);
    RESULT = m;

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-2)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // expression ::= NOMBRE 
            {
              Noeud RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		Integer n = (Integer)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    RESULT = new Const(n);

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expression ::= IDENTIFIANT 
            {
              Noeud RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    RESULT = new Idf(i);

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("expression",3, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // variable ::= VARIABLE_PLANETE IDENTIFIANT 
            {
              Noeud RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    RESULT = new Idf(i);

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("variable",4, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // variable ::= VARIABLE_LUNE IDENTIFIANT 
            {
              Noeud RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    RESULT = new Idf(i);

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("variable",4, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // variable ::= VARIABLE_NOUVELLE_LUNE IDENTIFIANT 
            {
              Noeud RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$ParserCup$stack.peek()).value;
		
    RESULT = new Idf(i);

              CUP$ParserCup$result = parser.getSymbolFactory().newSymbol("variable",4, ((java_cup.runtime.Symbol)CUP$ParserCup$stack.elementAt(CUP$ParserCup$top-1)), ((java_cup.runtime.Symbol)CUP$ParserCup$stack.peek()), RESULT);
            }
          return CUP$ParserCup$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$ParserCup$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$ParserCup$do_action(
    int                        CUP$ParserCup$act_num,
    java_cup.runtime.lr_parser CUP$ParserCup$parser,
    java.util.Stack            CUP$ParserCup$stack,
    int                        CUP$ParserCup$top)
    throws java.lang.Exception
    {
              return CUP$ParserCup$do_action_part00000000(
                               CUP$ParserCup$act_num,
                               CUP$ParserCup$parser,
                               CUP$ParserCup$stack,
                               CUP$ParserCup$top);
    }
}

}
