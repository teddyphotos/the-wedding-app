package com.example.tulips;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class card_fragment extends Fragment {
    private ImageView imageView;
    private TextView textView;
    ArrayList<ExampleItem> exampleList;
    int id;

    public card_fragment(int id){
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.verse_card_fragment,container,false);
        imageView = v.findViewById(R.id.imageView2);
        textView = v.findViewById(R.id.textView);
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.a, "மனிதர் ஆத்மஞானத்தை அடைதல் தேவர்களுக்குப்பிரியமில்லாதது” என்று ப்ருஹதாரண்ய கோபனிடத்திற் சொல்லியபடி மோக்ஷமார்கத்தில் செல்கின்றவர்களுக்கும் அதற்குபகரிகின்றவர்கட்க்கும் தேவர்கள் இடையூறு செய்யக்கூடுமாயினும் இந்த விவாஹ விஷயத்தில் தேவர்கள் எவ்வித இடையூறுகளையும் செய்யமாட்டார்கள். நான் ஒரு கன்னிகையை மணந்துகொண்டு ஸோமயாகம் முதலிய ஸத் கர்மங்களைச் செய்து இந்திரன் முதலிய தேவர்கட்கு ஹவிர்பாகங் கொடுப்பதை தேவராஜனான இந்திரன் மிக்க ஆவலுடன் கருதுவானாதலால் என்னை ஒரு கன்னிகையுடன் சேர்த்து இரட்டையாக்குவது இந்திரனுக்கும் சம்மதமாகவே இருக்கும். ஆகவே தேவராஜனுக்குச் சம்மதமான இக்காரியத்தில் பிரவிருத்தித்த தங்களுக்கு தேவர்களுடையவும், தேவராஜனுடையவும் உதவி கட்டாயம் ஏற்படுமாதலால்"));
        exampleList.add(new ExampleItem(R.drawable.b,"பிரஜாபத்யம், ஆஸுரம், காந்தர்வம், ராக்ஷஸம், பைசாசம், என்ற இவ்வைந்து விவாஹங்களுள் பிரஜாபத்யம், காந்தர்வம், ராக்ஷஸம் என்ற இம்மூன்று விவாஹங்களே தர்மத்தைவிட்டு விலகாதவைகள். மற்ற ஆஸுரம், பைசாசம் என்ற இரண்டு விவாஹங்களும் தர்மத்தைவிட்டு விலகினவைகளாக எண்ணப்படுகின்றன. ஆனது பற்றி பைசாசம், ஆஸுரம் என்ற இரண்டு விவாஹங்களும் எக்காலத்திலும் செய்யத்தகாதவைகளாகவே யாகும். இவ்விதம் இந்த ஸ்லோகத்திற் சொல்லி இருப்பதில் பிரஜாபத்யம் என்ற விவாஹம் க்ஷத்திரியன், வைச்யன், சூத்திரன் இவர்கட்கு இது வரை  விதிக்கப்படாததால் அது இந்தச் சுலோகத்தால் விதிக்கப்பட்டதாக ஏற்படுகிறது. வைச்ய சூத்திரர்கட்கு இதுவரையில் ராக்ஷஸ விவாஹம் விதிக்கப்படாததால் அதுவும் இங்கு விதிக்கப்பட்டதாக ஏற்படுகிறது. மேலும்"));
        exampleList.add(new ExampleItem(R.drawable.c,"என்றபடி முன் சொல்லப்பட்ட விவாஹங்கள் சேர்ந்தோ தனித்தனியாகவோ செய்யத்தகுந்தவைகளே ஆகும்; முற்கூறிய காந்தர்வம்; ராக்ஷஸம் என்ற இவ்விரண்டு விவாஹங்களும் க்ஷத்ரியனுக்கு தர்மத்தை விட்டு விலகாதவைகளாகவேயாகின்றது என்பது மனு முதலியோரின் கருத்தாக இருக்கின்றது. ஸ்திரீ புருஷர்கள் முதலில் ஒருவரையொருவர் நேசித்துப்பிறகு அதற்கு விரோதியாக இருப்பவர்களைப் போர்களத்தில்வென்று . அக்கன்னிகையைமணம் புரிவது காந்தர்வம், ராக்ஷஸம் என்ற இரண்டு விவாஹம் சேர்ந்ததாக ஏற்படுவதால் இவ்விவாஹமே காந்தர்வம், ராக்ஷஸம் என்ற இரண்டும் சேர்ந்த மிஸ்ரவிவாஹம் எனப்படும். இவ்விதம் கூறிய பிராம்மம் முதலிய 8 விவாஹங்களின் லக்ஷணமறிதல் அவச்யமாதல் பற்றி அதை உபதேசிக்கும் மனு வசனங்களை பின்னர் எழுதுவாம்"));
        exampleList.add(new ExampleItem(R.drawable.d,"கன்னிகைக்கும், வரனுக்கும் ஒருவருக்கொருவரிடம் உண்டான ஆசை காரணமாக ஆலிங்கனம் முதலியதையுடைய சேர்க்கையானது காந்தர்வ விவாஹம் எனப்படும். இந்த விவாஹம் ஒருவரையொருவர் நேசிப்பதன் மூலம் உண்டாவதால் மைதுனத்திற்கு (புணர்ச்சிக்கு) ஹிதமானதாகவுள்ளது. பொதுவாய் எல்லா விவாஹமும் புணர்ச்சிக்கு ஹிதமானதாகவே இருக்க இங்கு திரும்பவும்  என்று கூறியபடியால் ஒருவரையொருவர் நேசித்து முதலில் புணர்ச்சி ஏற்படினும் அதனால் எவ்வித விரோதமுமில்லை என்றும், காந்தர்வம் என்ற சாஸ்திரீய விவாஹமாகாவே அதைக்கொள்ளவேண்டும் என்றும் அதன் கருத்தென குல்லூகபட்டர் தமது உரையில் எழுதி இருக்கிறார்."));


        switch (id){
            case 1:
                try {
                    imageView.setImageResource(R.drawable.a);
                    textView.setText(R.string.translation1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    imageView.setImageResource(R.drawable.b);
                    textView.setText(R.string.translation2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    imageView.setImageResource(R.drawable.c);
                    textView.setText(R.string.translation3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                imageView.setImageResource(R.drawable.d);
                textView.setText(R.string.translation4);
                break;
            default:
                break;
        }


        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
