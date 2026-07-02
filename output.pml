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
proctype main() {
int tmp_div_0;
if
:: (0 == 0) ->
    divByZero = true;
:: else ->
    tmp_div_0 = 5 / 0;
fi;
int i = 2;
L0_start:
do
:: (i < 2) -> 
int x = tmp_div_0;

i++;

:: else -> break
od

}

init {
    run main();
}
ltl noDivZero { [](!divByZero) }
