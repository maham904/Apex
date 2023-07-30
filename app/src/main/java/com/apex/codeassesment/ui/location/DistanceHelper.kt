package com.apex.codeassesment.ui.location

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

// https://stackoverflow.com/a/3694410/2503185
// Example: distance(32.9697, -96.80322, 29.46786, -98.53506, 'M')
class DistanceHelper {
     fun distance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double,
        unit: Char
    ): Double {
        val theta = lon1 - lon2
        var dist =
            sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(
                deg2rad(lat2)
            ) * cos(deg2rad(theta))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        if (unit == 'K') {
            dist *= 1.609344
        } else if (unit == 'N') {
            dist *= 0.8684
        }
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

}