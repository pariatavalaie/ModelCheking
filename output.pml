// --- Promela Output ---
if
:: (x > 10) -> x = x - 1;

:: else ->
if
:: (x == 10) -> x = 1;

:: else ->
fi
fi
