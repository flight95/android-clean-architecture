# Android Material Design
[![Design: Material](https://img.shields.io/badge/Design-Material-orange.svg)](https://material.io/develop/android/)
> Android application should be applied
[Typography Theming](https://material.io/develop/android/theming/typography/),
[Color Theming](https://material.io/develop/android/theming/color/),
[Shape Theming](https://material.io/develop/android/theming/shape/) and
[Dark Theme](https://material.io/develop/android/theming/dark/).

## Typography Theming
[![Material: Typography](https://img.shields.io/badge/Material-Typography-orange.svg)](https://material.io/develop/android/theming/typography/)

The Material Design type scale. (Letter spacing values are compatible with Sketch.)
![Type scale](https://storage.googleapis.com/spec-host-backup/mio-design%2Fassets%2F1W8kyGVruuG_O8psvyiOaCf1lLFIMzB-N%2Ftypesystem-typescale.png)

In styles.xml.
```xml
<style name="style_text_headline1" parent="TextAppearance.MaterialComponents.Headline1" />
<style name="style_text_headline2" parent="TextAppearance.MaterialComponents.Headline2" />
<style name="style_text_headline3" parent="TextAppearance.MaterialComponents.Headline3" />
<style name="style_text_headline4" parent="TextAppearance.MaterialComponents.Headline4" />
<style name="style_text_headline5" parent="TextAppearance.MaterialComponents.Headline5" />
<style name="style_text_headline6" parent="TextAppearance.MaterialComponents.Headline6" />
<style name="style_text_subtitle1" parent="TextAppearance.MaterialComponents.Subtitle1" />
<style name="style_text_subtitle2" parent="TextAppearance.MaterialComponents.Subtitle2" />
<style name="style_text_body1" parent="TextAppearance.MaterialComponents.Body1" />
<style name="style_text_body2" parent="TextAppearance.MaterialComponents.Body2" />
<style name="style_text_caption" parent="TextAppearance.MaterialComponents.Caption" />
<style name="style_text_button" parent="TextAppearance.MaterialComponents.Button" />
<style name="style_text_overline" parent="TextAppearance.MaterialComponents.Overline" />
```

## Color Theming
[![Material: Color](https://img.shields.io/badge/Material-Color-orange.svg)](https://material.io/develop/android/theming/color/)

The baseline Material color theme.
![Color theme](https://storage.googleapis.com/spec-host-backup/mio-design%2Fassets%2F1hg4iTKzTMMgtJRx7bhaE2kSYR5BRYz1g%2Fcolor-colorsystem-schemecreation-theme.png)

In colors.xml.
```xml
<!-- Light theme brand colors -->
<color name="color_primary">#00bcd4</color>
<color name="color_primary_dark">#008ba3</color>
<color name="color_primary_variant">#008ba3</color>
<color name="color_on_primary">#f5f5f5</color>
<color name="color_secondary">#bcaaa4</color>
<color name="color_secondary_variant">#8c7b75</color>
<color name="color_on_secondary">#1b1b1b</color>

<!-- Light theme neutral colors -->
<color name="color_background">#f5f5f5</color>
<color name="color_on_background">#1b1b1b</color>
<color name="color_surface">#f5f5f5</color>
<color name="color_on_surface">#1b1b1b</color>
<color name="color_error">#9a0007</color>
<color name="color_on_error">#f5f5f5</color>
<color name="color_scrim">@color/mtrl_scrim_color</color> <!-- 32% opacity black -->
```

## Shape Theming
[![Material: Shape](https://img.shields.io/badge/Material-Shape-orange.svg)](https://material.io/develop/android/theming/shape/)

More research is needed.

In styles.xml.
```xml
<style name="style_shape_small" parent="ShapeAppearance.MaterialComponents.SmallComponent" />
<style name="style_shape_medium" parent="ShapeAppearance.MaterialComponents.MediumComponent" />
<style name="style_shape_large" parent="ShapeAppearance.MaterialComponents.LargeComponent" />
```

## Dark Theme
[![Material: DarkTheme](https://img.shields.io/badge/Material-DarkTheme-orange.svg)](https://material.io/develop/android/theming/dark/)

A dark theme is a low-light UI that displays mostly dark surfaces.
![Dark theme](https://storage.googleapis.com/spec-host-backup/mio-design%2Fassets%2F1Eb0bcqf3yyrabY8JLOrfC9zq_nN8wCu9%2Fdarktheme-overview.png)

Used values-night resource folder. Add colors.xml and themes.xml.

In /values/themes.xml.
```xml
<style name="Theme.App" parent="Theme.MaterialComponents.Light">
```

In /values-night/themes.xml.
```xml
<style name="Theme.App" parent="Theme.MaterialComponents">
```

# Android Navigation Architecture
[![Android: Navigation](https://img.shields.io/badge/Android-Navigation-yellow.svg)](https://developer.android.com/guide/navigation)

## Why Single Activity is better?
- You have to think about Activity and Fragment lifecycle together.
- Activity transitions sharing View are not smooth.
- The condition depends on who is the parent of the View.

Use Single Activity, But...
- There are components that need to be shared: [AppBarLayout](https://developer.android.com/reference/com/google/android/material/appbar/AppBarLayout), [FloatingActionButton](https://developer.android.com/reference/com/google/android/material/floatingactionbutton/FloatingActionButton), and [BottomNavigationView](https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView).

There are also Android library basic problems.
- [Android support library](https://developer.android.com/topic/libraries/support-library) should be converted [AndroidX](https://developer.android.com/jetpack/androidx).
- You have to think together: androidx.appcompat:appcompat and com.google.android.material:material.
- Not stabilized yet: AndroidX is v1.1.0, Material is v1.2.0-alpha01 for dark theme, and Navigation is v2.1.0-rc01.

There are some problems, but they are useful.

## Single NavHostFragment
[NavHostFragment](https://developer.android.com/guide/navigation/navigation-getting-started#add-navhost) is the basis for navigation.
Google samples offers two ways when using BottomNavigationView: [Single NavHostFragment](https://github.com/android/architecture-components-samples/tree/master/NavigationBasicSample), and swiching [Multiple NavHostFragment](https://github.com/android/architecture-components-samples/tree/master/NavigationAdvancedSample).
Switching Multiple NavHostFragment is too complicated. I will be used to Single NavHostFragment.

## Use NavHostFragment, AppBarLayout, FloatingActionButton, and BottomNavigationView together.
NavActivity, NavFragment and NavUI are provided by me to support this feature.
Please check the source in library-core.
Control the shared viewes via arguments in nav_graph.xml.

NavFragment should be call initRootViewPadding.
```kotlin
class HomeFragment : NavFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)
            ?.also { view -> initRootViewPadding(view) }

}
```

NavActivity should be call several init functions.
And you need to provide shared views.
```kotlin
class MainActivity : NavActivity() {
...
    private fun initActivity() {
        initBottomNavigationView()
        initToolbar()
        initTransientBottomLayout()
    }
...
    override fun getNavHostFragment(): Int = R.id.navHostFragment
    override fun getAppBarLayout(): AppBarLayout = appBarLayout
    override fun getCollapsingToolbarLayout(): CollapsingToolbarLayout = collapsingToolbarLayout
    override fun getMaterialToolbar(): MaterialToolbar = materialToolbar
    override fun getTopLevelDestination(): Set<Int> = setOf(...)
    override fun getPopupDestination(): Set<Int> = setOf()
    override fun getTransientBottomLayout(): CoordinatorLayout = transientBottomLayout
    override fun getFloatingActionButton(): FloatingActionButton = floatingActionButton
    override fun getBottomNavigationView(): BottomNavigationView = bottomNavigationView
...
}
```

This is the end. It's not complicated. Check the source in boilerplate-application.
