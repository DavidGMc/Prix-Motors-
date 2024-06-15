# Mantener la clase MyApp y todos sus métodos
-keep class com.androidavid.prixmotors.MyApp {
    *;
}

# Mantener la clase RetrofitClient y todos sus métodos
-keep class com.androidavid.prixmotors.RetrofitClient {
    *;
}

# Mantener las clases de Retrofit y sus convertidores
-keep class retrofit2.* { *; }
-keep class retrofit2.converter.gson.* { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Mantener la clase Products y todas sus propiedades
-keep class com.androidavid.prixmotors.model.Products {
    *;
}

# Mantener las clases que implementan Parcelable y sus miembros
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Mantener la interfaz ClutchAPI y todos sus métodos
-keep interface com.androidavid.prixmotors.ClutchAPI {
    *;
}

# Mantener la clase MainActivity y todos sus métodos
-keep class com.androidavid.prixmotors.ui.MainActivity {
    *;
}

# Mantener las clases de la biblioteca de navegación
-keep class androidx.navigation.** { *; }
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public <init>(...);
}
-keepattributes *Annotation*
-keep class androidx.fragment.app.FragmentContainerView { *; }

# Mantener las clases de ViewModel
-keep class androidx.lifecycle.ViewModel { *; }
-keep class com.androidavid.prixmotors.viewmodel.** { *; }
