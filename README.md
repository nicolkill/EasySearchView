# Easy SearchView
 [ ![Download](https://api.bintray.com/packages/nicolkill/android/EasySearchView/images/download.svg) ](https://bintray.com/nicolkill/android/EasySearchView/_latestVersion)
 [![Build Status](https://travis-ci.org/nicolkill/EasySearchView.svg?branch=master)](https://travis-ci.org/nicolkill/EasySearchView)

**Easy SearchView** is a library that to implement search in any view only setting the data.

## Features
  - Easy implementation
  - Good look

### ***For more functions or bugs, create a [issue](https://github.com/nicolkill/EasySearchView/issues).***

## **Usage**
### Step 1:
#### Add gradle dependecy
```
dependencies {
  compile 'com.nicolkill:easysearchview:{latest_release}'
}
```
### Step 2:
#### Add the following xml on your view:
```xml
<com.nicolkill.easysearchview.EasySearchView
    android:id="@+id/search_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
### Step 3:
#### Add ***SearchElement*** interface on your class, in this example i use [SuperRecyclerView](https://github.com/nicolkill/SuperRecyclerView) for the list:
```java
@LayoutResource(R.layout.row)
public class Option implements SearchElement {

    private static final String TAG = Option.class.getSimpleName();

    private int mOptionNumber;

    public Option(int number) {
        mOptionNumber = number;
    }

    @BindField(id = R.id.option_name)
    public String getOptionName() {
        return "Option " + (mOptionNumber + 1);
    }

    @Override
    public String toString() {
        return getOptionName();
    }

//  The following methods is of the interface

//  This define if the option can be showed on the search
    @Override
    public boolean isSearchPosibillity(String search) {
        return getOptionName().toLowerCase().contains(search.toLowerCase());
    }

//  get the principal object to use later
    @Override
    public Object getObject() {
        return this;
    }

//  the view resource on layouts
    @Override
    public int getViewRes() {
        return R.layout.row_search;
    }

//  this set the values on view
    @Override
    public void render(View view) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(getOptionName());
    }
}
```
### Step 4:
#### Get reference and set data:
```java
EasySearchView searchView = (EasySearchView) findViewById(R.id.search_container);
searchView.setData(data);
```
### Step 5:
#### Customize the adapter with the functions and listeners that you want
```java
// click
adapter.setOnClickListener(new ClickListener<Option>() {
    @Override
    public void onItemSelected(View view, int position, Option element) {
        Snackbar.make(view, "Click option selected: " + element.getOptionName(), Snackbar.LENGTH_SHORT).show();
    }
});
```
