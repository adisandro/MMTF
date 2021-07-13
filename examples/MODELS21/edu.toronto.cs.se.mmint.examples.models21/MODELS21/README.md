## MODELS 2021 paper



## Paper example instructions

1. Add `https://adisandro.github.io/mmint/release` to the list of software sites in Eclipse (`Help > Install New Software > Available Software Sites`).
2. Install `Examples > MMINT - MODELS21 paper`.
3. Open `/MODELS21/examples.middiag`.
4. Double-click on the yellow box named `sc : GSN` to open the GSN safety case.
5. FCS example:
    1. Right-click on the claim named `C1` and select `MMINT > Property Decomposition`.
    2. Select `Absence > $X is not reached` as the property to be decomposed, then select `State Damaged` for variable `$X`.
    3. Insert `2` as the number of sub-properties.
    4. Select `Transitions > Do not begin from $X` as the first sub-property, then select `State Damaged` for variable `$X`.
    5. Select `Transitions > Never transition into $X` as the second sub-property, then select `State Damaged` for variable `$X`.
6. Infusion Pump example:
    1. Right-click on the claim named `C2` and select `MMINT > Property Decomposition`.
    2. Select `Absence > $X is not reached after $A and until $B` as the property to be decomposed, then select `State BolusRequest` for variable `$X`, `Transition Cond_6_3?` for variable `$A`, `State Infusion_NormalOperation` for variable `$B`.
    3. Insert `3` as the number of sub-properties.
    4. Select `Response > If $X is reached, $Y must follow $X` as the first sub-property, then select `Transition Cond_6_3?` for variable `$X`, `State Alrm_EmptyReservoir` for variable `$Y`.
    5. Select `Absence > $X is not reached between $A and $B` as the second sub-property, then select `State BolusRequest` for variable `$X`, `Transition Cond_6_3?` for variable `$A`, `State Alrm_EmptyReservoir` for variable `$B`.
    6. Select `Absence > $X is not reached after $A and until $B` as the third sub-property, then select `State BolusRequest` for variable `$X`, `State Alrm_EmptyReservoir` for variable `$A`, `State Infusion_NormalOperation` for variable `$B`.
    