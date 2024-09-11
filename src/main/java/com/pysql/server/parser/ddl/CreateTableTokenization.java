package com.pysql.server.parser.ddl;

import com.pysql.server.parser.enums.Token;
import org.apache.calcite.sql.ddl.SqlCreateTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTableTokenization {

    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "(\\s+|"
                    + "CREATE|"
                    + "TABLE|"
                    + "COLUMN|"
                    + "[a-zA-Z_][a-zA-Z0-9_]*|"
                    + "\\(|"
                    + "\\)|"
                    + ",|"
                    + "[a-zA-Z]+|"
                    + "\\d+)"
    );

    public void analyzer(String sql) {

        List<Token> tokens = new ArrayList<>();

        Matcher matcher = TOKEN_PATTERN.matcher(sql);
        while (matcher.find()) {
            String tokenValue = matcher.group();
            if (!tokenValue.trim().isEmpty()) {

                if ("CREATE".equals(tokenValue)) {
                    tokens.add(new Token(Token.TokenType.CREATE, tokenValue));
                } else if ("TABLE".equals(tokenValue)) {
                    tokens.add(new Token(Token.TokenType.TABLE, tokenValue));
                } else if ("COLUMN".equals(tokenValue)) {
                    tokens.add(new Token(Token.TokenType.COLUMN, tokenValue));
                } else if (tokenValue.equals("(")) {
                    tokens.add(new Token(Token.TokenType.LPAREN, tokenValue));
                } else if (tokenValue.equals(")")) {
                    tokens.add(new Token(Token.TokenType.RPAREN, tokenValue));
                } else if (tokenValue.equals(",")) {
                    tokens.add(new Token(Token.TokenType.COMMA, tokenValue));
                } else if ("INT".equals(tokenValue.toUpperCase(Locale.ROOT))
                        || "VARCHAR".equals(tokenValue.toUpperCase(Locale.ROOT))
                        || "DATE".equals(tokenValue.toUpperCase(Locale.ROOT))) {
                    tokens.add(new Token(Token.TokenType.DATATYPE, tokenValue));
                } else if (tokenValue.matches("\\d+")) {
                    tokens.add(new Token(Token.TokenType.NUMBER, tokenValue));
                } else {
                    tokens.add(new Token(Token.TokenType.IDENTIFIER, tokenValue));
                }
            }
        }

        System.out.println(tokens);
    }
}
