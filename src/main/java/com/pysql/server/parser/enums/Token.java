package com.pysql.server.parser.enums;

/**
 *
 * @description: Token
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2024-09-11 17:31
 */
public class Token {

    public enum TokenType {
        CREATE, TABLE, COLUMN, IDENTIFIER, LPAREN, RPAREN, COMMA, DATATYPE, NUMBER, WHITESPACE
    }

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
