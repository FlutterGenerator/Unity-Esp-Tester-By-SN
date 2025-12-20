class Color {
public:
    float r, b, g, a;

    Color() {
        SetColor( 0 , 0 , 0 , 255 );
    }

    Color( float r , float g , float b ) {
        SetColor( r , g , b , 255 );
    }

    Color( float r, float g, float b, float a) {
        SetColor( r , g , b , a );
    }

    void SetColor( float r1 , float g1 , float b1 , float a1 = 255 ) {
        r = r1;
        g = g1;
        b = b1;
        a = a1;
    }

    static Color Black(float a = 255){ return Color(0, 0, 0, a); }
    static Color White(float a = 255){ return Color(255 , 255 , 255, a); }
    static Color Red(float a = 255){ return Color(255 , 0 , 0, a); }
    static Color Green(float a = 255){ return Color(0 , 255 , 0, a); }
    static Color Blue(float a = 255){ return Color(0 , 0 , 255, a); }
    static Color Yellow(float a = 255){ return Color(255 , 255 , 0, a); }
    static Color Cyan(float a = 255){ return Color(0 , 255 , 255, a); }
    static Color Magenta(float a = 255){ return Color(255 , 0 , 255, a); }
    
    static Color Brown(float a = 255){ return Color(121 , 85 , 72, a); }
    static Color Pink(float a = 255){ return Color(233 , 30 , 99, a); }
    static Color Lightblue(float a = 255){ return Color(3 , 169 , 244, a); }
    static Color Amber(float a = 255){ return Color(255 , 139 , 7, a); }
    static Color Teal(float a = 255){ return Color(0 , 150 , 136, a); }
    static Color Lime(float a = 255){ return Color(205 , 220 , 57, a); }
    
    static Color Purple(float a = 255){ return Color(156 , 39 , 157, a); }
    static Color DeepPurple(float a = 255){ return Color(103 , 58 , 183, a); }
    static Color Grey(float a = 255){ return Color(158 , 158 , 158, a); }
    static Color Indigo(float a = 255){ return Color(63 , 81 , 181, a); }
    static Color Orange(float a = 255){ return Color(255 , 152 , 0, a); }
    static Color DeepOrange(float a = 255){ return Color(255 , 87 , 34, a); }
    
};
