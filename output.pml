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

int x = 5 + 3 * 3 + tmp0 * 2;
bool b = true;
bool b = b && b || x && f;
proctype main() {
int tmp0;
power(4, 4, tmp0);
}

init {
    run main();
}
