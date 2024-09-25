package mo.bitcode.kyrie.util;

import ch.hsr.geohash.GeoHash;
import mo.bitcode.kyrie.common.model.GeoLocationAttributes;

public class GeoHashUtil {

  private static final int GEO_HASH_NUMBER_OF_CHARACTER = 5;

  public static String toGeoHash(GeoLocationAttributes geoLocationAttributes) {
    return toGeoHash(geoLocationAttributes, GEO_HASH_NUMBER_OF_CHARACTER);
  }

  public static String toGeoHash(GeoLocationAttributes geoLocationAttributes, int numberOfCharacter) {
    return toGeoHash(geoLocationAttributes.getLatitude(), geoLocationAttributes.getLongitude(), numberOfCharacter);
  }

  public static String toGeoHash(double latitude, double longitude) {
    return toGeoHash(latitude, longitude, GEO_HASH_NUMBER_OF_CHARACTER);
  }

  public static String toGeoHash(double latitude, double longitude, int numberOfCharacter) {
    return GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, numberOfCharacter);
  }

  public static int sort(double referenceLatitude, double referenceLongitude, GeoLocationAttributes g1, GeoLocationAttributes g2) {
    double distance1 = calculateDistance(referenceLatitude, referenceLongitude, g1.getLatitude(), g1.getLongitude());
    double distance2 = calculateDistance(referenceLatitude, referenceLongitude, g2.getLatitude(), g2.getLongitude());
    return Double.compare(distance1, distance2);
  }

  private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    // Haversine formula for distance calculation
    double R = 6371000; // Earth radius in meters
    double phi1 = Math.toRadians(lat1);
    double phi2 = Math.toRadians(lat2);
    double deltaPhi = Math.toRadians(lat2 - lat1);
    double deltaLambda = Math.toRadians(lon2 - lon1);

    double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
      Math.cos(phi1) * Math.cos(phi2) *
        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
  }

}
