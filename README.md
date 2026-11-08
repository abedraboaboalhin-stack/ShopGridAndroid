# ShopGrid Android App

A Java Android Studio project that recreates the product-listing screen from the supplied reference using `RecyclerView` and `GridLayoutManager` (two columns).

## Features
- Two-column product grid using `GridLayoutManager`
- Product cards with image, name, price, and cart icon
- Search field filters products as the user types
- Filter and cart controls provide demo Toast feedback
- Local vector drawable product illustrations; no image download or runtime permission needed

## Improvements in this version
- Edge-to-edge (Android 15) window insets handled so the header no longer overlaps the status bar
- Strings and colors moved to `strings.xml` / `colors.xml`
- `Product` price is now a `double`, formatted at display time; products have an `id`
- `ProductAdapter` uses `ListAdapter` + `DiffUtil` instead of `notifyDataSetChanged()`
- "No products found" empty state for searches with no results
- `.gitignore` added

## Open in Android Studio
1. Extract the ZIP.
2. Open the `ShopGridAndroid` folder in Android Studio.
3. Allow Gradle sync (Android Studio will use/generate the Gradle wrapper if prompted) and install any requested Android SDK components.
4. Run the `app` configuration on an emulator or Android phone with USB debugging enabled.

## Main implementation
- `MainActivity.java`: initializes RecyclerView and GridLayoutManager, creates sample data, and filters search results.
- `Product.java`: product model.
- `ProductAdapter.java`: RecyclerView adapter and ViewHolder.
- `activity_main.xml`: search/filter header and RecyclerView.
- `item_product.xml`: individual product card.

## GitHub submission
Create a new public GitHub repository, then upload the extracted project folder (including `app`, `build.gradle`, `settings.gradle`, and `README.md`). Do not upload the `.gradle` or `build` generated folders. Submit the repository URL and screenshots captured from the running app.
