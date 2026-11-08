package com.example.shopgrid;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private TextView emptyText;
    private final List<Product> allProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View rootLayout = findViewById(R.id.rootLayout);
        RecyclerView recyclerView = findViewById(R.id.productsRecyclerView);
        EditText searchInput = findViewById(R.id.searchInput);
        TextView filterText = findViewById(R.id.filterText);
        ImageView backButton = findViewById(R.id.backButton);
        ImageView gridButton = findViewById(R.id.gridButton);
        emptyText = findViewById(R.id.emptyText);

        applyWindowInsets(rootLayout, recyclerView);

        addSampleProducts();

        // Two-column grid matches the reference design.
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        adapter.submitList(new ArrayList<>(allProducts));

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }
            @Override public void afterTextChanged(Editable s) { }
        });

        filterText.setOnClickListener(v ->
                Toast.makeText(this, R.string.filter_toast, Toast.LENGTH_SHORT).show());
        backButton.setOnClickListener(v -> finish());
        gridButton.setOnClickListener(v ->
                Toast.makeText(this, R.string.grid_toast, Toast.LENGTH_SHORT).show());
    }

    /**
     * Android 15 (targetSdk 35) draws apps edge-to-edge, so the status bar and
     * navigation bar insets must be handled manually to avoid overlapping the UI.
     */
    private void applyWindowInsets(View rootLayout, RecyclerView recyclerView) {
        final int listBottomPadding = recyclerView.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (view, windowInsets) -> {
            Insets bars = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            view.setPadding(bars.left, bars.top, bars.right, 0);
            recyclerView.setPadding(
                    recyclerView.getPaddingLeft(),
                    recyclerView.getPaddingTop(),
                    recyclerView.getPaddingRight(),
                    listBottomPadding + bars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void addSampleProducts() {
        allProducts.add(new Product(1, "Bitzwolf B-CC1 Camera", 48.89, R.drawable.product_camera_tripod));
        allProducts.add(new Product(2, "Mini Instant Printer", 55.89, R.drawable.product_printer));
        allProducts.add(new Product(3, "Smart Indoor Camera", 78.89, R.drawable.product_indoor_camera));
        allProducts.add(new Product(4, "Compact Action Camera", 88.55, R.drawable.product_action_camera));
        allProducts.add(new Product(5, "HD Webcam", 23.67, R.drawable.product_webcam));
        allProducts.add(new Product(6, "Bitzwolf B-CC1 Camera", 44.89, R.drawable.product_camera_tripod));
    }

    private void filterProducts(String query) {
        String normalized = query.trim().toLowerCase(Locale.ROOT);
        List<Product> result = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase(Locale.ROOT).contains(normalized)) {
                result.add(product);
            }
        }
        adapter.submitList(result);
        emptyText.setVisibility(result.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
