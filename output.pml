L0_start:
do
:: (i == 5) -> 
L1_start:
do
:: (i == 2) -> 
i + 1
break;

i + 3

:: else -> break  
od;

i + 2
goto L0_start;


:: else -> break  
od;
