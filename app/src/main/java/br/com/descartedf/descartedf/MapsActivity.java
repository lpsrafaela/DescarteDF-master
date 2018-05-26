package br.com.descartedf.descartedf;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import android.location.Address;
import android.location.Criteria;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    final static int PERMISSION_ALL = 1;
    final static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private GoogleMap mMap;
    ImageView fundo;
    ImageView logo;
    ImageButton iconevoltar;
    TextView titulo_lugar,informacoes;
    double latitude;
    double longitude;
    LocationManager locationManager;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng origin = new LatLng(latitude, longitude);

                fundo.setVisibility(View.VISIBLE);
                iconevoltar.setVisibility(View.VISIBLE);
                titulo_lugar.setVisibility(View.VISIBLE);
                informacoes.setVisibility(View.VISIBLE);
                titulo_lugar.setText(marker.getTitle());
                informacoes.setText(marker.getSnippet());




                LatLng destination = marker.getPosition();

                DrawRouteMaps.getInstance(getApplicationContext())
                        .draw(origin, destination, mMap);

                mMap.clear();
                DrawMarker.getInstance(getApplicationContext()).draw(mMap, destination, R.drawable.marker, marker.getTitle());



                return false;
            }
        });


        iconevoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.clear();

                fundo.setVisibility(View.INVISIBLE);
                iconevoltar.setVisibility(View.INVISIBLE);
                titulo_lugar.setVisibility(View.INVISIBLE);
                informacoes.setVisibility(View.INVISIBLE);


                geraMarcador();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 13));
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 13));
        geraMarcador();


    }

    public void geraMarcador() {
        float zoomLevel = 14.0f;
        LatLng jarjourTaguatinga = new LatLng(-15.839957, -48.050569);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33521111   CEP: 7201585")
                .position(jarjourTaguatinga)
                .title("Posto Jarjour")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jarjourTaguatinga, zoomLevel));

        LatLng informaticaJunior = new LatLng(-15.691507, -47.825692);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)35419070   CEP: 70297400")

                .position(informaticaJunior)
                .title("Informática do Junior")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(informaticaJunior, zoomLevel));

        LatLng marista = new LatLng(-15.825287, -47.899272);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)34429400   CEP: 70200690")
                .position(marista)
                .title("Colégio Maristinha de Brasília")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marista, zoomLevel));

        LatLng anoes = new LatLng(-15.825743, -47.922801);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)32457288   CEP: 70384010")
                .position(anoes)
                .title("Posto dos Anões")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(anoes, zoomLevel));

        LatLng jarjourAsaSul = new LatLng(-15.820127, -47.908312);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)34432551   CEP: 70273000")
                .position(jarjourAsaSul)
                .title("Posto Jarjour")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jarjourAsaSul, zoomLevel));

        LatLng impConcursos = new LatLng(-15.811537, -47.883919);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)30299700   CEP: 70610410")
                .position(impConcursos)
                .title("IMP Concursos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(impConcursos, zoomLevel));

        LatLng brasasEnglishAsaSul = new LatLng(-15.821290, -47.910395);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)34421011   CEP: 70200050")
                .position(brasasEnglishAsaSul)
                .title("Brasas English")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brasasEnglishAsaSul, zoomLevel));

        LatLng clubeCoat = new LatLng(-15.817215, -47.874181);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)41414126   CEP: 70200003")
                .position(clubeCoat)
                .title("Academia Clube Coat")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clubeCoat, zoomLevel));

        LatLng laBoutique = new LatLng(-15.747372, -47.884524);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)34477494   CEP: 70876510")
                .position(laBoutique)
                .title("Padaria La Boutique")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(laBoutique, zoomLevel));

        LatLng jarjourAsaNorte = new LatLng(-15.768320, -47.881765);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33495036   CEP: 70844000")
                .position(jarjourAsaNorte)
                .title("Posto Jarjour")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jarjourAsaNorte, zoomLevel));

        LatLng restauraCar = new LatLng(-15.782655, -47.884365);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)34430432   CEP: 70362500")
                .position(restauraCar)
                .title("RestauraCar")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restauraCar, zoomLevel));

        LatLng brasasEnglishAsaNorte = new LatLng(-15.750847, -47.885664);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33494749   CEP: 70754510")
                .position(brasasEnglishAsaNorte)
                .title("Brasas English")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brasasEnglishAsaNorte, zoomLevel));

        LatLng atacadaoTaguatinga = new LatLng(-15.832701, -48.080837);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33369405   CEP: 72105508")
                .position(atacadaoTaguatinga)
                .title("Atacadão Taguatinga")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atacadaoTaguatinga, zoomLevel));

        LatLng atacadaoAsaNorte = new LatLng(-15.735020, -47.904856);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)34488400   CEP: 70770100")
                .position(atacadaoAsaNorte)
                .title("Atacadão Asa Norte")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atacadaoAsaNorte, zoomLevel));

        LatLng pananorteEletronica = new LatLng(-15.745477, -47.898799);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33473435   CEP: 70761630")
                .position(pananorteEletronica)
                .title("Pananorte Tecnologia Eletrônica")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pananorteEletronica, zoomLevel));

        LatLng carrefour512 = new LatLng(-15.750658, -47.894603);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)3340331   CEP: 70760502")
                .position(carrefour512)
                .title("Carrefour Bairro 512 norte")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carrefour512, zoomLevel));

        LatLng assaiAtacadista = new LatLng(-15.793167, -47.945997);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)32341773   CEP: 71200110")
                .position(assaiAtacadista)
                .title("Assaí Atacadista")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(assaiAtacadista, zoomLevel));

        LatLng assaiAtacadistaCeilandia = new LatLng(-15.820819, -48.101636);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33728112   CEP: 72215110")
                .position(assaiAtacadistaCeilandia)
                .title("Assaí Atacadista Ceilândia")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(assaiAtacadistaCeilandia, zoomLevel));

        LatLng carrefourSul = new LatLng(-15.830398, -47.955340);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33626804   CEP: 70297400")
                .position(carrefourSul)
                .title("Carrefour Brasília Sul")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carrefourSul, zoomLevel));

        LatLng carrefourBairroII = new LatLng(-15.818304, -47.912958);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33453772   CEP: 70364400")
                .position(carrefourBairroII)
                .title("Carrefour Bairro Asa Sul II")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carrefourBairroII, zoomLevel));

        LatLng carrefourBairroI = new LatLng(-15.809286, -47.884371);
        mMap.addMarker(new MarkerOptions().snippet("Telefone: (61)33216932   CEP: 70236400")
                .position(carrefourBairroI)
                .title("Carrefour Bairro Asa Sul I")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carrefourBairroI, zoomLevel));

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        fundo = findViewById(R.id.fundo);
        //logo = findViewById(R.id.logo);
        iconevoltar = findViewById(R.id.iconevoltar);
        titulo_lugar = findViewById(R.id.titulo_lugar);
        informacoes = findViewById(R.id.informacoes);
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionGranted()) {
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        } else requestLocation();
        if (!isLocationEnabled()) {
            showAlert(1);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }


        //
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }


            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }


            });




        }

    }


    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isPermissionGranted() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.v("mylog", "A permissão é concedida");
            return true;
        } else {
            Log.v("mylog", "Permissão não concedida");
            return false;
        }
    }

    private void showAlert(final int status) {
        String message, title = null, btnText;
        if (status == 1) {
            message = "Ativar localização?";
            btnText = "Ativar";
        } else {
            message = "O aplicativo DescarteDF necessita que a localização do dispositivo esteja ativada.";
            title = "Acesso à permissão";
            btnText = "Conceder";
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MapsActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        if (status == 1) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        } else
                            requestPermissions(PERMISSIONS, PERMISSION_ALL);
                    }
                })
                .setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }






}
