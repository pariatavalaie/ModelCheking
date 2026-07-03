inline power(base, exp, result) {
    int i;
    result = 1;
    i = 0;

    do
    :: (i < exp) ->
        result = result * base;
        i++
    :: else -> break
    od
}

bool divByZero = false;
int b = 0;
int x;
proctype main() {
int tmp_div_0;
int i = 0;
L0_start:
do
:: (i < 2) -> 
inLoop_0 : skip ;
if
:: (b == 0) ->
    divByZero = true;
:: else ->
    tmp_div_0 = 5 / b;
fi;
x = tmp_div_0;

i = i;

:: else -> break
od
exitLoop_0: skip;


}

init {
    run main();
}
ltl p1 { [] (!divByZero) }ltl p2 { [](main@inLoop_0 -> <>main@exitLoop_0) } 