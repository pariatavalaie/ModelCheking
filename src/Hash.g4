grammar Hash;

program: basteDecl*( biarDecl )*( topLevelDecl )*EOF;

basteDecl : Baste ID ( '.' ID )* ';';

biarDecl : Biar ID ('.'ID)*('.' '*')? ';' ;
topLevelDecl: klassDecl
             |functionDecl
             |varDecl ';';
//----------------------------------class------------------------------------------------
klassDecl : Klass CLASSID '{' klassBody* '}';

klassBody :( fields | functionDecl |constructor);

constructor:Bebin CLASSID '(' param?')' '{' stmt*  '}';

callConstructor:CLASSID '(' args? ')';

fields: varDecl ';' | objectInstantiation ';';

fieldAccess : ID '.' ID ;

methodCall: ID '.' callFunction;

constructorAccess: ID '.' callConstructor;
//----------------------------------object---------------------------------------------------------------------
objectInstantiation:CLASSID ID ( '=' Jadid callConstructor)?;
//------------------------------------function------------------------------------
functionDecl: voidfunction | returnfunction ;

param:type ID (',' type ID)*;

returnStmt : Bede exp ';';

returnfunction:Bebin type  ID '(' param?')' '{' stmt*  returnStmt'}' ;

voidfunction:Bebin Hich ID '(' param? ')' '{' stmt* '}';

callFunction:ID '(' args? ')';

args: exp ( ',' exp )*;


//----------------------------------------------------------------
varDecl:type ID ('=' exp)?;

type:Adad
    |Ashari
    |Boole
    |Harf
    |Matn
    ;
// ---------------------------------stmts--------------------------------------------------
stmt: ifStmt | exp ';'| varDecl';'| loopStmt | printStmt | inputStmt | switchStmt | callFunction ';' | objectInstantiation';' | breakStmt | continueStmt | exceptionHandeling | throwexception  ;

//--------------if-----------------------
ifStmt: Age '(' exp ')'  Bood '{'stmt*'}'
       ( Vagarna Age '(' exp ')'  Bood '{' stmt* '}' )*
       ( Vagarna '{'  stmt* '}' )? ;

//---------------loop-------------
loopStmt:whileStmt
        |forStmt;

whileStmt:Ta '(' exp ')' '{'  stmt* '}';

forStmt:Baraye '(' (varDecl|exp)? ';' exp ?';' exp? ')'
         '{' stmt* '}';



//---------------------------------------break_continiue---------------------------------------
breakStmt:Shekan ';';

continueStmt:Edame ';' ;
//-------------------------------switch---------------------------------------
switchStmt : Entekhab '(' exp ')' '{'
              caseStmt*
              defaultStmt? '}';

caseStmt : Halat  primary  '{' stmt* '}';
defaultStmt:  Digar '{' stmt* '}';
//------------------------expr-----------------------------------
exp : assign ;
assign : logicalOr (('=' | '+=' | '-=' | '*=' | '/=') assign)?;

logicalOr : logicalAnd ('||' logicalAnd)* ;

logicalAnd : equality ('&&' equality)* ;

equality : relational (('==' | '!=') relational)*;

relational: additive (('<' | '>' | '<=' | '>=') additive)*;

additive: multiplicative (('+' | '-') multiplicative)*;

multiplicative: power (('*' | '/' | '%') power)*;
power: unary ('**' power)?;
unary: ('!' | '+' | '-' | '++' | '--') unary| postfix;
postfix: primary ('++' | '--')*;
primary: '(' exp ')' | ID |Adad_Literal|Ashari_Literal|Matn_Literal|Harf_Literal |Boole_Literal | Khali |methodCall|fieldAccess|constructorAccess | callFunction | thisStmt ;
//--------------------------------------------print-----------------------------------------------------------------------
printStmt: Bechap '(' exp ')' ';';
inputStmt:Bekhan '(' type ID ')' ';';
//-----------------------this-----------------------
thisStmt:In '.' (ID|callFunction|callConstructor);
//-------------------------exception----------------------------------------------------------------------------------------------------
exceptionType: SefrBood | JadvalKhali| MahdoodeNadorost | GheireMojaz | CLASSID;

exceptionHandeling : Emtehan '{' stmt* '}'(catchexception)* (finallyexception)?;
catchexception:Gereftar '(' exceptionType ID ')' '{' stmt* '}';
finallyexception:Akhar '{' stmt* '}';
throwexception: Bendaz exceptionType '('')' ';';
//-------------------------------------------Lexer rules-------------------------------------------------------------------
Baste:'baste';
Biar:'biar';
Adad:'adad';
Ashari:'ashari';
Boole:'boole';
Matn:'matn';
Harf:'harf';
Hich:'hich';
Khali:'khali';
Age:'age';
Bood:'bood';
Vagarna:'vagarna';
Ta:'ta';
Baraye:'baraye';
Shekan:'shekan';
Edame:'edame';
Entekhab:'entekhab';
Halat:'halat';
Digar:'digar';
Bebin:'bebin';
Bede:'bede';
Klass:'klass';
In:'in';
Jadid:'jadid';
Emtehan:'emtehan';
Gereftar:'gereftar';
Akhar:'akhar';
Bendaz:'bendaz';
Bechap:'bechap';
Bekhan:'bekhan';
SefrBood:'SefrBood';
MahdoodeNadorost:'MahdoodeNadorost';
JadvalKhali:'JadvalKhali';
GheireMojaz:'GheireMojaz';

Ashari_Literal:[0-9]+ '.' [0-9]+ ;
Adad_Literal:[0-9]+;
Harf_Literal : '\'' . '\'';
Matn_Literal : '"' ( ~["\r\n] )* '"' ;
Boole_Literal:('dorost'|'ghalat');

ID  :[a-z][a-zA-Z0-9_]*;
CLASSID: [A-Z][a-zA-Z0-9_]*;


//------------comment------------
    LINE_COMMENT
        : '//' ~[\r\n]* -> skip
        ;

    MULTI_COMMENT
        : '/*' .*? '*/' -> skip
        ;
WS
    : [ \t\r\n]+ -> skip
    ;













