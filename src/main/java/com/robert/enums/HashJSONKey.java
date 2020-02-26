package com.robert.enums;

/**
 *
 * @author Roberto Armenta 
 * Enum used as a key in my ConcurrentHashMap where I
 * store the encoded JSONs LEFT and RIGHT defines the side in the map and RESULT
 * is the value that I get when I make the comparison
 */
public enum HashJSONKey {
    LEFT, RIGHT, RESULT
}
