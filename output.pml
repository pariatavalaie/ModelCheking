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
int x = 0;
proctype main() {
int tmp_div_0;
int i = 0;
L0_start:
do
:: (i < 2) -> 
int b = -2;

if
:: (b == 0) ->
    divByZero = true;
:: else ->
    tmp_div_0 = 5 / b;
fi;
x = tmp_div_0;

i++;

:: else -> break
od


}

init {
    run main();
}
ltl p1 { [] (!divByZero) }
ltl p4 { [](x >= 0) }