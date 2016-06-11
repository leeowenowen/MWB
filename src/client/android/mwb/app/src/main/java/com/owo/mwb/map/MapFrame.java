package com.owo.mwb.map;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.owo.android.util.ui.LayoutParamUtil;


public class MapFrame extends LinearLayout {

    private static final String TAG = "MapFrame";

    private MapView mMapView;
    private AMap mMap;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption = null;

    public MapFrame(Context context) {
        super(context);
        initComponents(context);
        setupListeners();
    }

    private void initComponents(Context context) {
        mMapView = new MapView(context);
        mMapView.setBackgroundColor(Color.TRANSPARENT);
        mMap = mMapView.getMap();
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
        //声明mLocationOption对象

        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        // mLocationClient.startLocation();

        setOrientation(LinearLayout.VERTICAL);
        addView(mMapView, LayoutParamUtil.LMM());
    }

    private class OnLocationChangedListenerWrapper implements AMapLocationListener {
        private boolean mIsActive;
        private LocationSource.OnLocationChangedListener mInner;

        public void updateInner(LocationSource.OnLocationChangedListener inner) {
            mInner = inner;
        }

        public void activate() {
            mIsActive = true;
        }

        public void deactivate() {
            mIsActive = false;
        }

        @Override
        public void onLocationChanged(AMapLocation location) {
            if (mIsActive && mInner != null) {
                mInner.onLocationChanged(location);
            }
        }
    }

    private MotionEvent mLastEv;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mLastEv = ev;
        return super.onInterceptTouchEvent(ev);
    }

    private void setupListeners() {
        mOnLocationChangedListener = new OnLocationChangedListenerWrapper();
        mLocationClient.setLocationListener(mOnLocationChangedListener);
        mOnLocationChangedListener.activate();

        LocationSource locationSource = new LocationSource() {
            @Override
            public void activate(final OnLocationChangedListener listener) {
                Log.v(TAG, "MainFrame:activate");
                mOnLocationChangedListener.updateInner(listener);
            }

            @Override
            public void deactivate() {
                Log.v(TAG, "MainFrame:deactivate");
                mOnLocationChangedListener.updateInner(null);
            }
        };
        mMap.setLocationSource(locationSource);
        // mMap.setMyLocationEnabled(true);
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {

            @Override
            public void onCameraChangeFinish(CameraPosition arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onCameraChange(CameraPosition arg0) {
                // TODO Auto-generated method stub

            }
        });

        mMap.setOnMapClickListener(new AMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub

            }
        });

        mMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {

            @Override
            public void onMapLoaded() {
                // TODO Auto-generated method stub

            }
        });

        mMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng arg0) {
                // TODO Auto-generated method stub

            }
        });

        mMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub

            }
        });

        mMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub

            }
        });
        mMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker mark) {
                //        MarkInfoView view = new MarkInfoView(getContext());
                //        view.setText(mark.getTitle());
                //        return view;
                return null;
            }

            @Override
            public View getInfoContents(Marker mark) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        mMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                //        final ConfigurableDialog dialog = new ConfigurableDialog(getContext(), false);
                //        FenTaQiaoTaView view = new FenTaQiaoTaView(getContext());
                //        view.setOnItemClickListener(new OnItemClickListener() {
                //          @Override
                //          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //            switch (position) {
                //              case 0:
                //                ItemView itemView = (ItemView) view;
                //                TU.setImageBitmap(BitmapId.location, itemView.mIcon);
                //                break;
                //              case 1:
                //                int offset = 20;
                //                TranslateAnimation leftTranslateAnimation =
                //                  new TranslateAnimation(0, offset, 0, offset);
                //                CycleInterpolator interpolator = new CycleInterpolator(4);
                //                leftTranslateAnimation.setInterpolator(interpolator);
                //                leftTranslateAnimation.setDuration(300);
                //                MainFrame.this.startAnimation(leftTranslateAnimation);
                //                break;
                //              default:
                //                break;
                //            }
                //          }
                //        });
                //        // setContentView first and setAttributes later
                //        dialog.setContentView(view);
                //        WindowManager.LayoutParams param = dialog.getWindow().getAttributes();
                //        param.width = DimensionUtil.w(250);
                //        param.height = WindowManager.LayoutParams.WRAP_CONTENT;
                //        param.dimAmount = 0;
                //        param.x = (int) mLastEv.getX();
                //        param.y = (int) mLastEv.getY();
                //        param.gravity = Gravity.LEFT | Gravity.TOP;
                //        dialog.getWindow().setAttributes(param);
                //        dialog.show();

                return true;
            }

            ;
        });
    }

    private OnLocationChangedListenerWrapper mOnLocationChangedListener;
    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {

        private Marker mMarker;

        @Override
        public void onLocationChanged(AMapLocation location) {

            if (location == null || location.getErrorCode() != 0) {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:" + location.getErrorCode() + ", errInfo:" +
                        location.getErrorInfo());
                return;
            }

            // test_begin
            //      if (mMarker == null) {
            //        Bitmap bg = Singleton.of(Theme.class).bitmap(BitmapId.main_location);
            //        Bitmap fg = Singleton.of(Theme.class).bitmap(BitmapId.common_app_logo);
            //        fg = BitmapHelper.createScaledBitmap(fg, DimensionUtil.w(34), DimensionUtil.w(34));
            //        BitmapCompositeUtil.composite(bg, fg, DimensionUtil.w(8), DimensionUtil.w(5));
            //        mMarker = mMap.addMarker(new MarkerOptions().title("冯囧囧你好!\n   ")
            //                                                    .anchor(0.5f, 0.5f)
            //                                                    .icon(BitmapDescriptorFactory.fromBitmap(bg))
            //                                                    .draggable(true));
            //      }
            //      mMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            //      // mMarker.setSnippet("GPS坐标:［latitude:" + location.getLatitude() +
            //      // "］[longitude:"
            //      // + location.getLongitude() + "]");
            //      try {
            //        mMarker.showInfoWindow();
            //      } catch (Exception e) {
            //        e.printStackTrace();
            //      }
            // test_end

            mOnLocationChangedListener.onLocationChanged(location);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
    }

    public void onDestroy() {
        mMapView.onDestroy();
    }


    public void onPause() {
        mMapView.onPause();
        mOnLocationChangedListener.deactivate();

        mLocationClient.stopLocation();
    }

    public void onResume() {
        mMapView.onResume();
        mOnLocationChangedListener.activate();

        mLocationClient.startLocation();
    }

    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}
