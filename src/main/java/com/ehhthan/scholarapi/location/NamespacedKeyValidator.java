package com.ehhthan.scholarapi.location;

import com.google.common.base.Preconditions;

import java.util.regex.Pattern;

// TODO: 12/6/2021 better
public class NamespacedKeyValidator {
    private static final Pattern VALID_NAMESPACE = Pattern.compile("[a-z0-9._-]+");
    private static final Pattern VALID_KEY = Pattern.compile("[a-z0-9/._-]+");

    public static void validateNamespace(String namespace) {
        Preconditions.checkArgument(VALID_NAMESPACE.matcher(namespace).matches(),
            "Namespace is invalid. Format must follow %s : %s", VALID_NAMESPACE, namespace);
    }

    public static void validateKey(String key) {
        Preconditions.checkArgument(VALID_KEY.matcher(key).matches(),
            "Key is invalid. Format must follow %s : %s", VALID_KEY, key);
    }

    public static void validateNamespacedKey(NamespacedKey namespacedKey) {
        validateNamespacedKey(namespacedKey.toString());
    }

    public static void validateNamespacedKey(String namespacedKey) {
        Preconditions.checkArgument(namespacedKey.length() < 256, "NamespacedKey length must be less than 256 characters: %s", namespacedKey);
    }
}
