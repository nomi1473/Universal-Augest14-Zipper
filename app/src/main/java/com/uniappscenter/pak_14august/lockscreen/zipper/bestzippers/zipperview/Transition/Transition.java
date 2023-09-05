package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Transition;

public class Transition {

    public static double GetValue(Transition_Type transition_type, double CurentTime, double F_pos, double T_pos, double Duration) {
        if (transition_type == Transition_Type.easeInCirc) {
            return (easeInCirc(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInCubic) {
            return (easeInCubic(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInExpo) {
            return (easeInExpo(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutCirc) {
            return (easeInOutCirc(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutCubic) {
            return (easeInOutCubic(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutExpo) {
            return (easeInOutExpo(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutQuad) {
            return (easeInOutQuad(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutQuart) {
            return (easeInOutQuart(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutQuint) {
            return (easeInOutQuint(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInOutSine) {
            return (easeInOutSine(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInQuad) {
            return (easeInQuad(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInQuart) {
            return (easeInQuart(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInQuint) {
            return (easeInQuint(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInSine) {
            return (easeInSine(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutCirc) {
            return (easeOutCirc(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutCubic) {
            return (easeOutCubic(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutExpo) {
            return (easeOutExpo(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutQuad) {
            return (easeOutQuad(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutQuart) {
            return (easeOutQuart(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutQuint) {
            return (easeOutQuint(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutSine) {
            return (easeOutSine(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.linearTween) {
            return (linearTween(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.Special1) {
            return (easeInOutQuad(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.Special2) {
            return (easeInOutQuart(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.Special3) {

            return (easeOutCubic(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.Special4) {

            return (easeOutSine(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeInBoune) {
            return (easeBounceIn(CurentTime, F_pos, T_pos - F_pos, Duration));
        } else if (transition_type == Transition_Type.easeOutbounce) {
            return (easeBounceOut(CurentTime, F_pos, T_pos - F_pos, Duration));
        }
        return (easeInOutSine(CurentTime, F_pos, T_pos - F_pos, Duration));


    }


    public static double linearTween(double t, double b, double c, double d) {
        return c * t / d + b;
    }

    // quadratic eaSing in - accelerating from zero velocity 
    public static double easeInQuad(double t, double b, double c, double d) {
        t /= d;
        return c * t * t + b;
    }

    // quadratic eaSing out - decelerating to zero velocity 
    public static double easeOutQuad(double t, double b, double c, double d) {
        t /= d;
        return -c * t * (t - 2) + b;
    }

    // quadratic eaSing in/out - acceleration until halfway, then deceleration
    public static double easeInOutQuad(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1)
            return c / 2 * t * t + b;
        t--;
        return -c / 2 * (t * (t - 2) - 1) + b;
    }

    // cubic eaSing in - accelerating from zero velocity
    public static double easeInCubic(double t, double b, double c, double d) {
        t /= d;
        return c * t * t * t + b;
    }

    // cubic eaSing out - decelerating to zero velocity
    public static double easeOutCubic(double t, double b, double c, double d) {
        t /= d;
        t--;
        return c * (t * t * t + 1) + b;
    }

    // cubic eaSing in/out - acceleration until halfway, then deceleration 
    public static double easeInOutCubic(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1) return c / 2 * t * t * t + b;
        t -= 2;
        return c / 2 * (t * t * t + 2) + b;
    }

    // quartic eaSing in - accelerating from zero velocity 
    public static double easeInQuart(double t, double b, double c, double d) {
        t /= d;
        return c * t * t * t * t + b;
    }

    // quartic eaSing out - decelerating to zero velocity
    public static double easeOutQuart(double t, double b, double c, double d) {
        t /= d;
        t--;
        return -c * (t * t * t * t - 1) + b;
    }

    // quartic eaSing in/out - acceleration until halfway, then deceleration
    public static double easeInOutQuart(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1) return c / 2 * t * t * t * t + b;
        t -= 2;
        return -c / 2 * (t * t * t * t - 2) + b;
    }

    // Sinusoidal eaSing in - accelerating from zero velocity
    public static double easeInSine(double t, double b, double c, double d) {
        return (-c * Math.cos(t / d * (Math.PI / 2)) + c + b);
    }

    // Sinusoidal eaSing out - decelerating to zero velocity
    public static double easeOutSine(double t, double b, double c, double d) {
        return (c * Math.sin(t / d * (Math.PI / 2)) + b);
    }

    // Sinusoidal eaSing in/out - accelerating until halfway, then decelerating
    public static double easeInOutSine(double t, double b, double c, double d) {
        return (-c / 2 * (Math.cos(Math.PI * t / d) - 1) + b);
    }

    // exponential eaSing in - accelerating from zero velocity 
    public static double easeInExpo(double t, double b, double c, double d) {
        return (c * Math.pow(2, 10 * (t / d - 1)) + b);
    }

    // exponential eaSing out - decelerating to zero velocity
    public static double easeOutExpo(double t, double b, double c, double d) {
        return (c * (-Math.pow(2, -10 * t / d) + 1) + b);
    }

    // exponential eaSing in/out - accelerating until halfway, then decelerating 
    public static double easeInOutExpo(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1) return (c / 2 * Math.pow(2, 10 * (t - 1)) + b);
        t--;
        return c / 2 * (-Math.pow(2, -10 * t) + 2) + b;
    }

    // circular eaSing in - accelerating from zero velocity 
    public static double easeInCirc(double t, double b, double c, double d) {
        t /= d;
        return -c * (Math.sqrt(1 - t * t) - 1) + b;
    }

    // circular eaSing out - decelerating to zero velocity
    public static double easeOutCirc(double t, double b, double c, double d) {
        t /= d;
        t--;
        return c * Math.sqrt(1 - t * t) + b;
    }

    // circular eaSing in/out - acceleration until halfway, then deceleration
    public static double easeInOutCirc(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1) return -c / 2 * (Math.sqrt(1 - t * t) - 1) + b;
        t -= 2;
        return c / 2 * (Math.sqrt(1 - t * t) + 1) + b;
    }

    // quintic easing in/out - acceleration until halfway, then deceleration 
    public static double easeInOutQuint(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1) return c / 2 * t * t * t * t * t + b;
        t -= 2;
        return c / 2 * (t * t * t * t * t + 2) + b;
    }

    // quintic easing in - accelerating from zero velocity 
    public static double easeInQuint(double t, double b, double c, double d) {
        t /= d;
        return c * t * t * t * t * t + b;
    }

    // quintic easing out - decelerating to zero velocity
    public static double easeOutQuint(double t, double b, double c, double d) {
        t /= d;
        t--;
        return c * (t * t * t * t * t + 1) + b;
    }


    // bounce easing in
    public static double easeBounceIn(double t, double b, double c, double d) {
        return c - easeBounceOut(d - t, 0, c, d) + b;
    }

    // bounce easing Out
    public static double easeBounceOut(double t, double b, double c, double d) {
        if ((t /= d) < (1 / 2.75)) {
            return c * (7.5625 * t * t) + b;
        } else if (t < (2 / 2.75)) {
            return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
        } else if (t < (2.5 / 2.75)) {
            return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
        } else {
            return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b;
        }
    }
}
