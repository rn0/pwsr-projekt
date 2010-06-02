package chatServer;

/**
 * User: Piotr Kapera
 * Date: 2010-06-02
 * Time: 20:59:46
 */
public enum ChannelMode {
    s, // Secret channel—not shown in channel list or user whois except to users already on the channel
    m, // Channel is moderated (only those who hold operator or voice status on the channel can send messages to it)
    l; // Limit number	Limits number of users able to be on channel (when full, no new users can join)
}
