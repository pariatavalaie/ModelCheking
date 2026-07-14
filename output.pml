inline power(base, exp, result) {
    int q;
    result = 1;
    q = 0;

    do
    :: (q < exp) ->
        result = result * base;
        q++
    :: else -> break
    od
}

bool divByZero = false;
bool endReached = false;

int x = 10;
proctype main() {
int tmp_div_0;
int tmp_mod_1;
int tmp2;
int y = 3;
int divisor = 3;
int sum = 0;
int difference = 0;
int product = 0;
int quotient = 0;
int remainder = 0;
int powerResult = 0;
int result = 0;
int i = 0;
int j = 0;
int outer = 0;
int inner = 0;
bool trueValue = true;
bool falseValue = false;
bool lessCheck = false;
bool lessEqualCheck = false;
bool greaterCheck = false;
bool greaterEqualCheck = false;
bool equalCheck = false;
bool notEqualCheck = false;
bool logicalCheck = false;
bool exceptionHandled = false;
sum = x + y;
difference = x - y;
product = x * y;
if
:: (divisor == 0) ->
    divByZero = true;
:: else ->
    tmp_div_0 = x / divisor;
fi;
quotient = tmp_div_0;
if
:: (divisor == 0) ->
    divByZero = true;
:: else ->
    tmp_mod_1 = x / divisor;
fi;
remainder = tmp_mod_1;
power(y, 3, tmp2);
powerResult = tmp2;
x = x + 2;
x = x - 2;
x = x * 2;
if
:: (2 == 0) ->
    divByZero = true;
:: else ->
    x = x / 2;
fi;
i++;
i--;
lessCheck = y < x;
lessEqualCheck = y <= x;
greaterCheck = x > y;
greaterEqualCheck = x >= y;
equalCheck = x == y;
notEqualCheck = x != y;
logicalCheck = (lessCheck && greaterCheck) || (greaterEqualCheck && !falseValue);
if
:: (logicalCheck) ->
result = sum;
:: else ->
result = difference;
fi;

i = 0;
L0_start:
do
:: (i < 8) -> 
inLoop_0 : skip ;
i = i + 1;

if
:: (i == 2) ->
goto L0_start;

:: else -> skip;
fi;


sum = sum + i;

if
:: (sum > 25) ->
break;

:: else -> skip;
fi;



:: else -> break  
od
exitLoop_0: skip;

j = 0;
;
L1_start:
do
:: (j < 6) -> 
inLoop_1 : skip ;
product = product + j;

if
:: (j == 3) ->
break;

:: else -> skip;
fi;


j = j + 1;

:: else -> break
od
exitLoop_1: skip;

outer = 0;
L2_start:
do
:: (outer < 3) -> 
inLoop_2 : skip ;
inner = 0;

L3_start:
do
:: (inner < 5) -> 
inLoop_3 : skip ;
inner = inner + 1;

if
:: (inner == 2) ->
break;

:: else -> skip;
fi;



:: else -> break  
od
exitLoop_3: skip;


outer = outer + 1;


:: else -> break  
od
exitLoop_2: skip;

bool err_1 = false;
if
:: (quotient == 3) ->
err_1 = true;

:: else -> skip;
fi;

result = 100;
if
:: err_1 -> {
exceptionHandled = true;
result = 0 - 1;
}
:: else -> skip
fi;
err_1 = false;

if
:: (exceptionHandled) ->
result = result + 1;
:: else ->
result = 0;
fi;

if
:: (x > 0) ->
if
:: (y > 0) ->
if
:: (x > y) ->
result = 1;
:: else ->
result = 2;
fi;

:: else ->
int z = 1;
result = 3;
fi;

:: else ->
result = 4;
fi;

endReached=true; 
}

init {
    run main();
}
