public class Division implements DivisionService {

    private StringBuilder result = new StringBuilder();
    private StringBuilder quotient = new StringBuilder();
    private StringBuilder reminder = new StringBuilder();

    public String divide(long dividend, long divider) throws IllegalArgumentException, NullPointerException {

            if (divider == 0) {
                return "Divider cannot be 0, division by zero";
            }

            long absDividend = Math.abs(dividend);
            long absDivider = Math.abs(divider);

            if (absDividend < absDivider) {
                return "Dividend less than divider. Column Division isn't possible";
            }

            String[] dividendAsArray = String.valueOf(absDividend).split("");

            Integer reminderAsDigit;
            long multiplyResult;

            Integer dividerDigit = calculateDigit(absDivider);
            long mod;

            for (int i = 0; i < dividendAsArray.length; i++) {
                reminder.append(dividendAsArray[i]);
                reminderAsDigit = Integer.parseInt(reminder.toString());

                if (reminderAsDigit >= absDivider) {
                    mod = reminderAsDigit % absDivider;
                    multiplyResult = reminderAsDigit / absDivider * absDivider;

                    String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderAsDigit.toString());
                    result.append(lastReminder).append("\n");

                    String multiply = String.format("%" + (i + 2) + "d", multiplyResult);
                    result.append(multiply).append("\n");

                    Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
                    result.append(makeDivider(multiplyResult, tab)).append("\n");

                    quotient.append(reminderAsDigit / absDivider);

                    reminder.replace(0, reminder.length(), Long.toString(mod));
                    reminderAsDigit = Integer.parseInt(reminder.toString());
                } else {
                    if (i >= dividerDigit) {
                        quotient.append(0);
                    }
                }

                if (i == dividendAsArray.length - 1) {
                    result.append(String.format("%" + (i + 2) + "s", reminderAsDigit.toString())).append("\n");
                }
            }

            modifyResultToView(absDividend, absDivider);

        if (dividend > 0 && divider < 0) {
            result.insert(0, "-");
            return result.toString();
        }
        if (dividend < 0 && divider > 0) {
            result.insert(0, "-");
            return result.toString();
        } else return result.toString();
    }

    private String makeDivider(long reminderNumber, Integer tab) {
        return assemblyString(tab, ' ') + assemblyString(calculateDigit(reminderNumber), '-');
    }

    private void modifyResultToView(long dividend, long divider) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }
            if (j == 3) {
                break;
            }
        }

        int tab = calculateDigit(dividend) + 1 - index[0];
        result.insert(index[2], assemblyString(tab, ' ') + "│" + quotient.toString());
        result.insert(index[1], assemblyString(tab, ' ') + "│" + assemblyString(quotient.length(), '-'));
        result.insert(index[0], "│" + divider);
        result.replace(1, index[0], Long.toString(dividend));
    }

    private int calculateDigit(long i) {
        return (int) Math.log10(i) + 1;
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }
}




