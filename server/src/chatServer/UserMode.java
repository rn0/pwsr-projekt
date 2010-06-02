package chatServer;

/**
 * User: Piotr Kapera
 * Date: 2010-06-01
 * Time: 08:55:54
 */

// http://chaoticjava.com/posts/tricks-with-enums/

public enum UserMode {
    i, // Invisible—cannot be seen without a common channel or knowing the exact name
    o, // @ User is a channel operator
    h, // % User is a half-op (half-operator)
    v, // + User is a voiced user. See +m
    u; // default mode
}
