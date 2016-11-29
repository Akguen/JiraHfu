package com.jira.jirahfu.model;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class Avatar {

    private String avatarURL4848;
    private String avatarURL3232;
    private String avatarURL2424;
    private String avatarURL1616;

    public Avatar(String avatarURL4848, String avatarURL3232,
                  String avatarURL2424, String avatarURL1616) {
        this.avatarURL4848 = avatarURL4848;
        this.avatarURL3232 = avatarURL3232;
        this.avatarURL2424 = avatarURL2424;
        this.avatarURL1616 = avatarURL1616;
    }

    public String getAvatarURL1616() {
        return avatarURL1616;
    }
}
