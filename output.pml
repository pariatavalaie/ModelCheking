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

proctype main() {
int tmp0;
int tmp1;
power(2, 3, tmp0);
power(4, (1 + tmp0), tmp1);
int i = 2;
L0_start:
do
:: (i < 2) -> 
int x = 5 + 3 * 3 + tmp1;

i++;

:: else -> break
od

}

init {
    run main();
}
