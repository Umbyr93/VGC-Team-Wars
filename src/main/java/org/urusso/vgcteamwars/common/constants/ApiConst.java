package org.urusso.vgcteamwars.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConst {
    private static final String API = "api";
    private static final String V1 = "v1";
    private static final String SEP = "/";
    private static final String API_V1 = API + SEP + V1;

    public static final String USER_API = API_V1 + SEP + "users";
}
