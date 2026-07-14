bool divByZero = false;
bool endReached = false;

int x = 0;
proctype main() {
bool err_1 = false;
x = 10;
err_1 = true;

if
:: err_1 -> {
x = 1;
}
:: else -> skip
fi;
err_1 = false;

endReached=true; 
}

init {
    run main();
}
