package com.himanshu.journalApp.service;



public class BodyForMail {


    public static String sentimentBody(String User , String sentiment){
        switch (sentiment){
            case  "Neutral"
                    : return "Hello "+ User  +
                    "We noticed your journal entries reflect a mostly neutral tone over the past week. This suggests you’ve been maintaining balance in your emotions. Neutral days are equally important, giving your mind and body the rest they deserve. Take this time to appreciate stability and focus on small things that bring you joy. Even if life feels routine, each step you take matters. We encourage you to keep journaling—it helps track subtle changes in your mindset. Stay mindful, and remember: balance is progress too.";

            case "Happy"
                    : return "Hello "+ User  +
                    "Your journals this week reflect a strong sense of happiness! That’s wonderful to see. Being in a happy state not only uplifts your spirit but also inspires people around you. Continue nurturing this positivity through activities you enjoy, connecting with loved ones, or setting new goals. Writing about your achievements, gratitude, and good moments will help you remember and relive them later. Keep celebrating these wins, big or small—they shape a fulfilling journey. Thank you for sharing your reflections with us. May the coming days bring you even more happiness.";

            case "Very Happy"
                : return "Hello "+ User  +
                    "We’re delighted to see that your journals radiate very high levels of happiness this week! Your energy and positive mindset shine through your reflections. This is the perfect time to channel that joy into creative pursuits, meaningful projects, or even sharing positivity with friends and family. Journaling during such moments can become an inspiring record for you in the future. Embrace the good times fully and keep building memories you’ll cherish. We hope this wave of positivity continues for you in the days ahead. Keep smiling and spreading happiness wherever you go!";

            case "Sad"
                :return  "Hello "+ User  +
                    "Your journals this past week reflect feelings of sadness. It’s okay to have difficult days—acknowledging them is the first step toward healing. Remember, emotions are part of being human. Consider writing about what specifically weighs you down, as journaling often brings clarity. Talking to a trusted friend or loved one may also lighten the burden. Please know that you’re not alone—we’re here to support your journey. Small steps like deep breathing, short walks, or gratitude writing can make a difference. Take care of yourself, and remember brighter days always follow darker ones.";

            case "Very Sad"
                : return "Hello "+ User  +
                    "We’ve noticed your journals reflect deep sadness this week. It’s important to acknowledge your emotions and give yourself the space to heal. Please know that it’s completely okay to seek help—whether by confiding in someone you trust, talking to a professional, or practicing self-care activities. Journaling is a safe outlet, but you don’t have to carry heavy emotions alone. Remember, every difficult phase is temporary, even if it doesn’t feel that way right now. Be gentle with yourself—you deserve compassion and care. Please prioritize your well-being, and never hesitate to reach out for support.";

            default: return "Cant do Analysis";
        }
     }
}
