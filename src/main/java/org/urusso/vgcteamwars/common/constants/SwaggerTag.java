package org.urusso.vgcteamwars.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SwaggerTag {
    public static final String AUTH_TAG_NAME = "AUTH API";
    public static final String AUTH_TAG_DESC = "Auth management";

    public static final String USER_TAG_NAME = "USER API";
    public static final String USER_TAG_DESC = "Users management";

    public static final String TEAM_TAG_NAME = "TEAM API";
    public static final String TEAM_TAG_DESC = "Teams management";

    public static final String WAR_TAG_NAME = "WAR API";
    public static final String WAR_TAG_DESC = "Wars management";
}
