class Solution {
    private String expr;
    private int pos;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.pos = 0;
        TreeSet<String> result = parseUnion();
        return new ArrayList<>(result);
    }

    // union := concat (',' concat)*
    private TreeSet<String> parseUnion() {
        TreeSet<String> result = new TreeSet<>();
        result.addAll(parseConcat());
        while (pos < expr.length() && expr.charAt(pos) == ',') {
            pos++; // skip ','
            result.addAll(parseConcat());
        }
        return result;
    }

    // concat := factor+
    private Set<String> parseConcat() {
        List<Set<String>> factors = new ArrayList<>();
        while (pos < expr.length() && expr.charAt(pos) != ',' && expr.charAt(pos) != '}') {
            factors.add(parseFactor());
        }
        Set<String> result = new HashSet<>();
        result.add("");
        for (Set<String> factor : factors) {
            Set<String> next = new HashSet<>();
            for (String prefix : result) {
                for (String word : factor) {
                    next.add(prefix + word);
                }
            }
            result = next;
        }
        return result;
    }

    // factor := letter | '{' union '}'
    private Set<String> parseFactor() {
        if (expr.charAt(pos) == '{') {
            pos++; // skip '{'
            Set<String> result = parseUnion();
            pos++; // skip '}'
            return result;
        } else {
            // sequence of lowercase letters forms one word (single token)
            int start = pos;
            while (pos < expr.length() && Character.isLowerCase(expr.charAt(pos))) {
                pos++;
            }
            Set<String> single = new HashSet<>();
            single.add(expr.substring(start, pos));
            return single;
        }
    }
}