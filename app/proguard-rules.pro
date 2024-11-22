# ----------------------------
# Rules for Retrofit2
# ----------------------------
# Keep Retrofit API interfaces
-keep interface retrofit2.** { *; }

# Keep Retrofit methods and annotations
-keepattributes Signature
-keepattributes *Annotation*

# Prevent obfuscation of Retrofit Response and Call objects
-keep class retrofit2.** { *; }

# Gson serialization/deserialization used with Retrofit
-keep class com.google.gson.annotations.** { *; }

# ----------------------------
# Rules for Kotlinx Serialization
# ----------------------------
# Keep metadata for Kotlinx Serialization
-keepclassmembers class kotlinx.serialization.** { *; }
-keepnames class kotlinx.serialization.** { *; }

# Keep annotations used by Kotlinx Serialization
-keepattributes *Annotation*

# ----------------------------
# Rules for Kotlinx DateTime
# ----------------------------
# Keep Kotlinx DateTime classes
-keep class kotlinx.datetime.** { *; }

# ----------------------------
# Rules for Hilt (Dagger-Hilt)
# ----------------------------
# Keep Hilt-generated components
-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }

# Keep Hilt annotations
-keepattributes *Annotation*

# Prevent stripping of Hilt modules and entry points
-keep class * {
    @dagger.hilt.InstallIn *;
}

# ----------------------------
# Rules for Jetpack Compose
# ----------------------------
# Keep Jetpack Compose Compiler-generated classes
-keep class androidx.compose.** { *; }

# Keep Compose runtime annotations
-keepattributes *Annotation*

# Keep metadata for Composables
-keep @androidx.compose.runtime.Composable class * { *; }

# ----------------------------
# Rules for Coil (Image Loading Library)
# ----------------------------
# Suppress warnings for missing Coil3 classes
-dontwarn coil3.PlatformContext

# Keep Coil3 classes
-keep class coil3.** { *; }
-keepattributes *Annotation*

# Keep Coil3 networking classes
-keep class coil3.network.** { *; }

# Keep OkHttp classes used by Coil3
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# General Kotlin rules
-keep class kotlin.Metadata { *; }
-keepclassmembers class ** {
    *;
}

# ----------------------------
# General Android and Kotlin Rules
# ----------------------------
# Keep Kotlin metadata for reflection
-keep class kotlin.Metadata { *; }
-keepclassmembers class ** {
    *;
}

# Prevent stripping of Enums
-keepclassmembers enum * { *; }

# Keep Android Parcelable classes
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

# ----------------------------
# Optional: Debug Logging
# ----------------------------
# Remove debug logs (optional for release builds)
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}
