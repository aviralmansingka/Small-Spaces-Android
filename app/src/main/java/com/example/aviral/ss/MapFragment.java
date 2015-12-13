package com.example.aviral.ss;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment that launches other parts of the demo application.
 */
public class MapFragment extends Fragment {

    String s = "{\"collection\": [ " +
            "{\"artist\":\"Zach Medler\",\"desc\":\"1.    The artist of “Sunday Morning Wabash River” is Zach Medler, the curator of Small Spaces Lafayette. This piece is a recreation of “A Sunday Afternoon on the Island of La Grande Jatte,” which is on display at the Art Institute in Chicago. Medler mentioned that one of his motifs throughout his artwork, which is evident in this piece, is to take classic paintings and add a twist to them. This specific art piece is on an extremely large scale, completely viewable from the street and surrounds Bernadette’s Barbershop. The size, location and artwork itself brings an artistic vibe to an artistic business. When you look closely, you can see that the characters in the artwork are on mobile devices, showing Medler’s thoughts on how technology is absorbing our lives.\", \"geo_lat\":\"40.4189319\", \"geo_long\":\"-86.89487767\", \"id\":\"1\", \"image_location\":\"img_01.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgardner\",\"desc\":\"The artist of this piece is Aaron Bumgarner. His Tumblr shows the variety of artwork he created for Small Spaces and on his own. Throughout the city of Lafayette, you will see many different signs posted up, and most of these signs were created by Aaron. This particular piece is posted up on a street post, making it necessary for you to look up to see the artwork. Located right next to a stoplight at an intersection, it is put in the perfect spot for cars passing by to see as well as pedestrians walking the street. The signs all say different things, and are usually are positive messages or compliments such as this one. Try going around the city to find some of Aaron's other signs!\", \"geo_lat\":\"40.41987803\", \"geo_long\":\"-86.89433289\", \"id\":\"2\", \"image_location\":\"img_02.png\"}," +
            "" +
            "{\"artist\":\"Lisa Wicka\",\"desc\":\"This piece is by Purdue alum and current Assistant Professor of Art at University of Wisconsin Colleges, Lisa Wicka. She has a personal passion for patterns, which she featured in this piece. The geometry of the art interacts with the geometry of the building beyond just the lines of the panes of glass. For example, the wide red line is an extension of the fire escape stairway, and several of the lines run parallel to that. The teal pattern section not only relates to the stairway, but appears to come out of the vent. This piece incorporates components of the building with the art which joins the canvas with the content. Missing and broken sections of glass just further the relationship between the art and the building and add to the abstract, jagged geometry.\", \"geo_lat\":\"40.42007323\", \"geo_long\":\"-86.89256559\", \"id\":\"3\", \"image_location\":\"img_03.png\"}," +
            "" +
            "{\"artist\":\"Paul Meadows\",\"desc\":\"Local tattoo artist Paul Meadows, co-owner of The Tattooed Heart, painted this mural for Matty in 2014. His traditional tattoo style is clearly apparent in this piece, brightening up the wall. It is painted on a boarded up window from the Haywood Printing Co. Inc, directly next to a mural painted by another co-owner of The Tattooed Heart, Robert Brumbaugh. The styles and colors compliment each other well and add a bit of cohesiveness to the street art. Most pieces vary in design and colors from one to the next, which is expected from the variety of people painting them. However, these two pieces are so consistent and interrelated, it is tough to not to be drawn to both. This piece, along with the other, work extremely well with the space, adding a bright and cartoonish atmosphere to an otherwise bland and old building.\", \"geo_lat\":\"40.42007381\", \"geo_long\":\"-86.89240489\", \"id\":\"4\", \"image_location\":\"img_04.png\"}," +
            "" +
            "{\"artist\":\"Richard Brumbaugh\",\"desc\":\"This Richard Brumbaugh piece resides on the building considered by the curator to be the center of Small Spaces. With windows on all sides, this building is home to many of the Small Spaces artworks. This piece in particular highlights one of the main initiatives of Small Spaces. Brumbaugh, a local tattoo artist, was sought out by Medler in order to showcase artists from the Lafayette community. Brumbaugh’s style is inherited from classic sailor tattoos, and those motifs are evident in this piece. Combined with the surrounding artworks and Brumbaugh’s, Small Spaces has completely transformed an old factory building into a notable community gathering place. With local flair and international variety, Small Spaces has taken a normal building and brought creativity to the city of Lafayette.\", \"geo_lat\":\"40.42008872\", \"geo_long\":\"-86.89234184\", \"id\":\"5\", \"image_location\":\"img_05.png\"}," +
            "" +
            "{\"artist\":\"Kevin Burdick\",\"desc\":\"Created by Kevin Burdick, a street artist who studied at the Art Institute of Pittsburgh and has had his artwork on the cover American Iron, a Harley Davidson magazine, this image gazes out into the street and instantly steals your attention away from anything else. The face in this art piece belongs to the well-known movie star, Clint Eastwood. His green undertones and customary Clint Eastwood stern gaze bring to life your favorite cowboy, drill sergeant, or astronaut. Definitely the most awe inspiring piece in the row, Burdick’s painting naturally draws you back toward the old warehouse as Eastwood’s gaze follows you down the street. Along with the other pieces of art, this painting puts life back into an otherwise plain building. If you notice the archway the painting is in, Eastwood appears to guard the building from what could possibly be an old entrance to the building.\", \"geo_lat\":\"40.42010769\", \"geo_long\":\"-86.89225587\", \"id\":\"6\", \"image_location\":\"img_06.png\"}," +
            "" +
            "{\"artist\":\"Jason Profant\",\"desc\":\"Created by a tattoo artist, Jason Profant, this painting looks like it might jump off the wall and start chugging down the street. This image connects to the strong ties the city of Lafayette has with the railroad. Profant’s artistic talent is shown in this image with an amazing glowing light on the front of the train and a serene alpine landscape in the background. Surrounded by grim images, Profant’s painting brings a lighter, happier element to the street while bringing to life a large, steam powered train. The enthusiastic face on the train makes you smile and remember your childhood experiences. This work of art could depict the green train engine Henry from the Thomas the Tank Engine series, who once had a red overcoat on him.\", \"geo_lat\":\"40.42010916\", \"geo_long\":\"-86.89218835\", \"id\":\"7\", \"image_location\":\"img_07.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"Zach Medler (a.k.a. ZMED) is not only the artist of this specific piece, but is also the founder and curator of the Small Spaces Lafayette project.  Just a few years ago his goal of reenergizing and reconnecting his hometown with street art has now become a reality, as Zach continues to work on and grow Small Spaces Lafayette. His piece seen here, called, “American Neo-Gothic” is inspired by Grant Wood’s “American Gothic,” a wildly famous and often parodied 20th century painting. Notice how Zach integrates the air conditioner and locates it on the second floor of the house, as it at first glance appears to be a window or regular aspect of the house. Medler’s piece, surrounded by a barren and industrialized area of Lafayette, won’t scream out at you, but is brilliantly done by juxtaposing the “old” versus the “new,” in reference to the technologically dependent society we live in today.\", \"geo_lat\":\"40.42010398\", \"geo_long\":\"-86.89213249\", \"id\":\"8\", \"image_location\":\"img_08.png\"}," +
            "" +
            "{\"artist\":\"Bethany Hohman\",\"desc\":\"The artist of this mural, Bethany Hohman, is known for her whimsical, and adorable characters. She likes to create creatures with a story, and asks viewers to discover the stories, or “simply enjoy the cuteness” as stated in one of her interviews. This particular mural depicts TOWBY, which is an acronym for “the one who was born yesterday”, on the Haywood Printing Building, near Bernadette’s Barbershop as part of a larger collection of works. Bethany’s unique art style distinguishes this work from others nearby as the adorable and lovable TOWBY, provides a strong contrast to the grim Clint Eastwood mural, and the ironic painting by Zach Medler nearby. The degree to which it fits into space is interesting since it makes use of the air conditioner and window by giving it wings. The AC appears to be unplugged from its natural position, and its color helps to separate it from the content of the mural.\", \"geo_lat\":\"40.4200939\", \"geo_long\":\"-86.89197278\", \"id\":\"9\", \"image_location\":\"img_09.png\"}," +
            "" +
            "{\"artist\":\"Brock Richert\",\"desc\":\"This Brock Richert piece is stationed on the central building of the Small Spaces project. Painted around an air conditioner, its patterns resemble electronic circuitry and the energy flowing out of the machinery. Aside from an interesting coherence with its surroundings, it is worth noting that Richert is a former Purdue student. Small Spaces has aimed, from the start, to connect with the younger residents of West Lafayette, particularly Purdue students. Pieces such as Richert’s help bridge that gap and create a connection between the cities of Lafayette and West Lafayette. Purdue and Lafayette have much different demographics and Lafayette has long sought a way to reach the students of Purdue. With continuing initiatives West Lafayette and Lafayette may soon relate to one another more than they have in the past.\", \"geo_lat\":\"40.4200939\", \"geo_long\":\"-86.89197278\", \"id\":\"10\", \"image_location\":\"img_10.png\"}," +
            "" +
            "{\"artist\":\"Anonymous\",\"desc\":\"The artist of the above piece is unknown. This piece is not part of the Small Spaces Project but was inspired by it. This piece of graffiti is in the form of a post-up. A post-up is a piece of graffiti artwork that is printed on paper and then put up using wall paper adhesive. Post-ups are meant to be temporary and this serves to indicate that the artist is expecting it to be short lived.  It changes the look of the white and slightly dusty shutters behind it by giving it a feel of old and an impression that is meant to look run-down.\", \"geo_lat\":\"40.42014419\", \"geo_long\":\"-86.89193985\", \"id\":\"11\", \"image_location\":\"img_11.png\"}," +
            "" +
            "{\"artist\":\"Kevin Burdick\",\"desc\":\"This artwork is done by Kevin Burdick. Burdick is a local tattoo artist who has contributed to Small Spaces with another piece, “Eastwood.” When providing artwork to Small Spaces, Burdick is consistent with the use of faces and vibrancy. This piece is on a large scale wall on an open sidewalk for all of the community to see. The artwork completely transforms the space by the use of color. Most of the colors used are vibrant, and pop against the dark brick wall surrounding it. While walking past this on the street, it truly captures your attention. The artwork pulls you in and makes you feel mysterious and out of this world. The blue skin truly transforms this piece from just a portrait to a landmark people plan to visit. Take time to notice the artwork surrounding this particular piece.\", \"geo_lat\":\"40.42025057\", \"geo_long\":\"-86.89194291\", \"id\":\"12\", \"image_location\":\"img_12.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"Co-created by Lafayette local and former Purdue University student, Aaron Bumgarner, and Indianapolis artist, Pete Brown, this piece shows a creative take on street art. On a quiet street, this piece combines an inspirational message with irony, while accenting the building it is placed on. Take a minute to really analyze this piece and how it changes your feelings. If you notice the piece of glass broken out of the window, it creates an interesting addition to the effect this painting. You might not think much of it at first but Bumgarner and Brown’s painting has much to offer. This piece blends blends in on a low traffic street but actually has many exciting aspects to it.\", \"geo_lat\":\"40.42032566\", \"geo_long\":\"-86.89191586\", \"id\":\"13\", \"image_location\":\"img_13.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"This piece of artwork was created by Aaron Bumgarner. It is a spray paint variation of the famous image of Muhammad Ali standing triumphantly over his rival, Sonny Liston. Ali had knocked Liston down with a seemingly insignificant punch that almost no one saw land, and he was yelling at Liston to get up and fight. This highly anticipated match lasted on two minutes and twelve seconds, and it is still debated how Ali won so quickly with what became known in the sports world as the “Phantom Punch.” The artwork readily draws the eye of passersby because they likely recognize the iconic pose. It provides a contrast with the nearby artwork by presenting something much more raw and gritty. It is also interesting to notice how Muhammad Ali is portrayed with a halo around his head, as if he is an angel. Consider why the artist would use such imagery.\", \"geo_lat\":\"40.4203383\", \"geo_long\":\"-86.89190626\", \"id\":\"14\", \"image_location\":\"img_14.png\"}," +
            "" +
            "{\"artist\":\"Temre Stanchfield\",\"desc\":\"This artwork is done by Temre Stanchfield. Stanchfield is an oil painter who has worked in Lafayette at different galleries and exhibitions. The art piece is the outline of the shadow of a nearby tree that is cast on this window at a certain time of day. This is on a street with multiple other art pieces, incorporating artistic unity in that particular place. It is on a medium size scale and is completely viewable from the street and sidewalk. There is a sense of nature when viewing this artwork, almost as if being in the forest, and it makes an urban area feel more natural and rural. There is also a calming feeling when viewing this piece, which may be from its simplicity. Try to visit this piece in the afternoon to see the full effect of the shadow casted upon the window.\", \"geo_lat\":\"40.42036657\", \"geo_long\":\"-86.89190348\", \"id\":\"15\", \"image_location\":\"img_15.png\"}," +
            "" +
            "{\"artist\":\"Sagan Newham\",\"desc\":\"The original piece painted here was created by local artist Sagan Newham.  The artwork that used to be here was called “Walking Men”, and it showed a crowd of bald, zombie-like figures slowly fading away into nothing.  However, the artwork was eventually painted over, leaving the current blank canvas that is present today.  In the absence of artwork, holes have been punched in the wall, and scribbles from other people can occasionally be found around the space.  The stark white paint stands out against the dull bricks of the building, attracting the attention of passerby.  This piece is unique among the other artwork because it is actually the absence of any art that actually creates a place around the area.  Small Spaces has expressed interest in possibly painting a new mural here again someday in the future.  Only time will tell what kind of new art will be used to fill in the gap that this piece currently occupies.\", \"geo_lat\":\"40.42025057\", \"geo_long\":\"-86.89194291\", \"id\":\"16\", \"image_location\":\"img_16.png\"}," +
            "" +
            "{\"artist\":\"Cathryn English\",\"desc\":\"Created by a local artist, Cathryn English, this astounding piece looms over the alleyway. Located on a boarded window of the Haywood Printing Co. Inc. building, this piece is unlike the other paintings on the building, which are generally bright while this one has dark tones and an almost menacing aura. It is in complete contrast to the painting directly left of it, as one is dark with harsh contrast and the latter is bright with soft colors. The piece seems to stand guard over the alleyway, confronting passersby and drivers alike. The gaze of the character seems to follow people when they walk or drive by. While this painting definitely adds some darkness and bleakness to the alleyway, it does offer insight to people by getting them to think about their futures and when they will make their dreams a reality.\", \"geo_lat\":\"40.4204531\", \"geo_long\":\"-86.89200715\", \"id\":\"17\", \"image_location\":\"img_17.png\"}," +
            "" +
            "{\"artist\":\"Carrie Wing\",\"desc\":\"Carrie Wing, the artist of this mural, was a Purdue student who graduated with a dual major in Fine Art and Art Education. Her inspirations ranged from David Hockney to David Byrne, with most of her stylistic inspiration coming from the city of New Mexico and graphic novels, which is evident in this mural through her unique use of color. As a former Purdue Student, she wishes to make Lafayette a better place through her art. The overall color palette of this work is quite earthy, making it fit in better with the wall around it. To further that effect, the dark blue borders blend in with the surroundings and provides contrast for the main content of the mural, making it noticeably pop in its surroundings. The droplets of dark blue paint on the window sill give the illusion that it was painted recently.\", \"geo_lat\":\"40.42046198\", \"geo_long\":\"-86.89206578\", \"id\":\"18\", \"image_location\":\"img_18.png\"}," +
            "" +
            "{\"artist\":\"Alejandra Carrillo Munoz\",\"desc\":\"This mural was painted on the back wall of Haywood Printing Building by Lafayette native Alejandra Carrillo Munoz. She is a Purdue graduate and a dress designer by profession. Many of her pieces, including this mural, were influenced by her Latin American identity. This painting attracts a lot more people to the backyard which is generally full of cars. One can observe the different nuances of each mural which are part of a collection of four decorating previously boarded-up windows. This painting is not only very distinctive from the other three pieces of art but also from all the other artwork of Small Spaces. This contributes to the curator’s agenda of beautifying the rundown areas in Lafayette in a diverse manner. This piece is one of the curator’s favorite pieces and its color scheme stands out as it depicts the fierceness of the color red. This piece is one of the curator’s favorite pieces.\", \"geo_lat\":\"40.420471\", \"geo_long\":\"-86.89209116\", \"id\":\"19\", \"image_location\":\"img_19.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"This piece of artwork was created by Aaron Bumgarner using paint on a window. It depicts a blue bird in flight. Bumgarner dedicated this piece to his mother, which is likely why it appears much more serene and peaceful than some of his other works. For example, Bumgarner’s spray paint image of Muhammad Ali (artwork # 14) is much cruder and does not have the same amount of color found in this bird image. This contrast shows his versatility as an artist. Notice how the eye-catching color and patterns of the art piece manage to both contrast with the dark brick of the building, while at the same time meshing with the patterned nature of the brick. The patterns that radiate outward from the flying bird are beautiful in their hand drawn symmetry; they are coherent and flow together while at the same time their imperfections maintain originality.\", \"geo_lat\":\"40.42048012\", \"geo_long\":\"-86.89211072\", \"id\":\"20\", \"image_location\":\"img_20.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"Created by Aaron Bumgarner, this is one of the smaller of the artwork and is harder to find. Bumgarner has created many pieces with the same style - white and black abstract hands - all over Lafayette as well as West Lafayette. This piece continues to hold true to his style. Located beneath a fan box on a boarded up window of Haywood Printing Co. Inc., this piece is one of many on the building but is quite different from all the others. This piece is a paste-up which is art that was done on a canvas and then glued to a wall. Every other piece is painted and permanent while this one is temporary. The one hand appears to be pointing further down the alleyway, as if to lead a viewer through the alley to more artwork, or motivating someone to continue forward. People who enjoy this piece can view more of Bumgarner’s work throughout Small Spaces.\", \"geo_lat\":\"40.42048891\", \"geo_long\":\"-86.89215951\", \"id\":\"21\", \"image_location\":\"img_21.png\"}," +
            "" +
            "{\"artist\":\"TAF's After School Program\",\"desc\":\"A group of student from the TAF’s After School Arts Program designed and installed this mural. Each of the students had their own individual ideas and as a class came together and created this piece that incorporates each of their whimsical thoughts into one coherent piece. This piece and its neighbor play off of each other in a way of forming together an array of thoughts into an enticing piece of art. Some of the aspects that stand out include a rock out Statue of Liberty, the famed keyboard cat, and the “spider pig” from the Simpson’s movie walking upside-down in the top-right corner.\", \"geo_lat\":\"40.42048084\", \"geo_long\":\"-86.89223553\", \"id\":\"22\", \"image_location\":\"img_22.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This piece of artwork, known as “Robot Yearbook,” was created by Zach Medler (ZMED), the founder of the Small Spaces art project. The piece was created using spray paint and covers a large window of a building with each of the individual window panes serving as the frame for a robot face. Medler has other works that also center on robots; he says that he enjoys making art that is in relation to technology. This piece is located on a large building that has many other Small Spaces art pieces, the different styles of which create an exciting mural of various colors and shapes. All these pieces make the building a destination; people see one piece, then are drawn to another, and soon they are traversing downtown Lafayette in search of all the other Small Spaces art. It is also interesting to consider the uniqueness present in “Robot Yearbook”; each robot is given its own look through the use of different shapes.\", \"geo_lat\":\"40.42042734\", \"geo_long\":\"-86.8923794\", \"id\":\"23\", \"image_location\":\"img_23.png\"}," +
            "" +
            "{\"artist\":\"Paul Meadows & Richard Brumbaugh\",\"desc\":\"Done by local tattoo artists Paul Meadows and Richard Brumbaugh, this piece has more of a tattoo style. It livens up the old window with smiling characters and bright colors. Being in an open parking lot, many people who walk by are able to see the piece. This old parking lot is transformed into a place where people can stop and look at the artwork on the wall. The building gains some attention on the back from this piece, which is rather large compared to some and is very noticeable. The smiling bird and slightly evil-looking flower have a pleasing color scheme and fun appearance. If you look around, you’ll see that this piece is viewable from all angles of the parking lot due to its large size and placement.\", \"geo_lat\":\"40.42041247\", \"geo_long\":\"-86.89242679\", \"id\":\"24\", \"image_location\":\"img_24.png\"}," +
            "" +
            "{\"artist\":\"Lisa Wicka\",\"desc\":\"Lisa Wicka, a member of the Purdue University community, painted this creative piece. The artist has contributed several different pieces of artwork to Small Spaces, all of which share her unique, patterned style. Lisa has a keen interest in textile making, which is clearly reflected in her patterned painting. Look at how the lines in the artwork line up perfectly with lines on the building: The red lines follow the rails from the fire escape, and the pink rectangles mimic the cutouts in the windows around the building. Evidently, from her use of the buildings’ lines, Lisa was able to see the beauty and gain inspiration from a rundown, downtown building. There are several printing companies in the downtown Lafayette area, and this textile-inspired piece may remind onlookers of the artful textiles created inside of these drab buildings.\", \"geo_lat\":\"40.41993733\", \"geo_long\":\"-86.89162935\", \"id\":\"25\", \"image_location\":\"img_25.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This piece, titled “This is My Social Media”, was painted by Zach Medler, the curator of Small Spaces. The image depicts a man with a can of spray paint painting the piece’s title on the building. This can be compared to how all of the artists of Small Spaces left their own individual marks on their buildings through their artwork. Through this piece, Medler communicates that the art that he does is his form of social media, meaning it is how he likes to express himself to the world. The art also fits the general purpose of the Small Spaces project by showing how artists can create places by using their art.  The location of this piece is interesting because it talks about being a form of media, while itself being high up on a wall near the printing building for newspapers, yet another form of media.\", \"geo_lat\":\"40.41989243\", \"geo_long\":\"-86.8916008\", \"id\":\"26\", \"image_location\":\"img_26.png\"}," +
            "" +
            "{\"artist\":\"Baron Mattern & Kaleb Lucas\",\"desc\":\"Baron Mattern and Kaleb Lucas collaborated to create this piece of art. It was one of the first Small Spaces pieces to include a three dimensional aspects. Walk closer to the wall and notice the octagon island in the water is actually raised from the surface. This piece has very high contrasting colors from the building it’s painted on and is relatively close to the street, adding color and catching people’s attention whenever they walk or drive by the area. Some interesting aspects of this art that adds to the overall surrealism of the piece are the repetitive colors and the creature in the top right corner that slightly resembles a pig.\", \"geo_lat\":\"40.41986849\", \"geo_long\":\"-86.89160203\", \"id\":\"27\", \"image_location\":\"img_27.png\"}," +
            "" +
            "{\"artist\":\"Jeri Foley\",\"desc\":\"Created by artist Jeri Foley, this difficult to find piece can be spotted on a wall behind a dumpster. It’s location as well as the black and white color scheme of the piece keep it hidden, but it is well worth the effort to find. The piece depicts fantasy creatures, drawn from the depths of Foley’s imagination. The five animals on the edges appear to all be some type of dog, while the creature in the middle is an interesting mixture of multiple animals. Consider the different features present on the creature: it has horns, the beak of a pelican, a feathery mane, another head emerging from its mouth, and even a strange type of tail. The piece draws your eyes away from the dumpster and parking lot that it is located in, and draws you into corner of fantasy.  Additionally, it is thought-provoking to consider that the similar animals clustered on the edges of the piece are all staring at the unique creature that is striding through the middle.\", \"geo_lat\":\"40.41984419\", \"geo_long\":\"-86.89162486\", \"id\":\"28\", \"image_location\":\"img_28.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"Look up to find this message from former Purdue University student and Lafayette local, Aaron Bumgarner. Bumgarner has created many signs featuring short messages like this one on telephone poles throughout the downtown area. The inspirational messages were put up to make you stop and look and maybe even smile. However, signs like this one were frequently dismantled by city workers who mistook them for vandalism. Whether this piece makes you stop and smile or laugh at the rhyme, this sign is a great example of what Small Spaces is about. It turns an otherwise unknown location into a place for people to enjoy their community and brighten their day. This piece could possibly include a religious reference to the book of Luke or it may just be metaphor for you to ponder over.\", \"geo_lat\":\"40.41999168\", \"geo_long\":\"-86.89164676\", \"id\":\"29\", \"image_location\":\"img_29.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"This unique piece can be difficult to find. It can’t be found on a wall, window, or door, like most of the other art from Small Spaces, but instead can be found posted to a telephone pole. Local artist Aaron Bumgarner felt his approach not only spoke to the message of Small Spaces, but was a unique twist on modern street art. His piece is especially unique considering it is not the only one of its kind. Bumgarner’s work can be found posted up on various telephone poles all throughout downtown. Each piece is slightly different, but all follow a generally positive if not sometimes comical theme. When Small Spaces Lafayette was still a young project, the first few signs put up by Bumgarner were taken down by city workers, who at first didn’t know they were a part of the project. Now, they can be found throughout Lafayette for all to see.\", \" geo_lat\":\"40.41998527\", \"geo_long\":\"-86.89133201\", \"id\":\"30\", \"image_location\":\"img_30.png\"}," +
            "" +
            "{\"artist\":\"Anonymous\",\"desc\":\"This hand-sized piece of unknown origin says less about placemaking, and more about the impact of Small Spaces. Though it is not an official part of the project, it speaks to Small Spaces’ goal of promoting community and the incorporation of art into the city. It shows the citizens of Lafayette’s willingness and ability to positively contribute to its growing art scene. The “stuff gone bye” annotation narrates the space this piece occupies as it is too small and inconspicuously placed to stop people from passing it up. Even the soft lines and dull colors contribute to its hiding place in plain sight at the end of an alleyway.\", \"geo_lat\":\"40.4199751\", \"geo_long\":\"-86.89129356\", \"id\":\"31\", \"image_location\":\"img_31.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"One of many similar signs painted by Aaron Bumgarner that can be found high up on poles around the Lafayette area, this piece brings a message from Small Spaces. This particular sign has a pleading message, crying out that it does not want to be taken down, and that it should instead just be left alone where people can see it. It is meant to draw the attention of passersby to itself and other artwork and request them to take notice and appreciate the art that has been created around them. This simple sign has created a place around the pole that it is mounted on by transforming a normally irrelevant post into an object that has a voice, which merely asks for people to take notice of and support the changes that the art has made in the area.  All of Bumgarner’s signs in the area have this similar aspect about them.\", \"geo_lat\":\"40.42000201\", \"geo_long\":\"-86.89115411\", \"id\":\"32\", \"image_location\":\"img_32.png\"}," +
            "" +
            "{\"artist\":\"Students at Jeff High School\",\"desc\":\"This piece was created and installed by students in the art club at Lafayette Jeff High School. The artwork consists of multiple paste-ups, which will wear away over time and eventually be covered up by new paste-ups. This will create a layered effect on the piece of new on top of old. This piece almost blends into its environment because of how torn up and worn away it looks, but when one looks closer the most colorful sections stand out and contrast with their surroundings. Some notable aspects of the art include the mostly black and white color scheme with the occasional bright accent colors on parts of the piece such as a red clown nose, a blue balloon, or a yellow shirt. The center section of the piece reflects the title of the artwork, “Mosey Main.” In the most torn up area, one can see a city street during Lafayette’s summer festival “Mosey Down Main street”.\", \"geo_lat\":\"40.41995483\", \"geo_long\":\"-86.8912918\", \"id\":\"33\", \"image_location\":\"img_33.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"Hidden in an alleyway behind Lafayette Printing Co. is a creation of Aaron Bumgarner’s. Aaron has contributed multiple pieces to the Small Spaces project, many of which incorporate sketches of hands like the ones that can be seen flowing out of the water pipe. There are several hands painted throughout this piece, which all flow freely from the gushing water, and seem to be reaching for something. Notice how the largest hand on the bottom right of the painting is being grabbed at the wrist by another hand. The use of white paint on the black wall of the alley contrasts with the dark space, brightening it and bringing onlookers’ attention to free flowing water, a natural element not otherwise found on the downtown streets of Lafayette.\", \"geo_lat\":\"40.41991958\", \"geo_long\":\"-86.89126326\", \"id\":\"34\", \"image_location\":\"img_34.png\"}," +
            "" +
            "{\"artist\":\"Aaron Molden\",\"desc\":\"Aaron Molden created this piece. It was originally a marker drawing that was later copied, scaled up, and printed out to be pasted on the wall. It fits with the many paper flies that surround it on various spots around the building and city.The paper design is meant to peel and fade as time passes creating a dilapidated effect. The flies, created by Zach Medler can serve as a sort of scavenger hunt around the city. If you are feeling adventurous, try to find them all. The placement of this paste up is significant because it makes the bland boarded-up window pop. There is also a distinct contrast between the earthy tones of the brick wall and the stark black and white of the wolf.\", \"geo_lat\":\"40.41987617\", \"geo_long\":\"-86.8912266\", \"id\":\"35\", \"image_location\":\"img_35.png\"}," +
            "" +
            "{\"artist\":\"Aaron Molden\",\"desc\":\"Aaron Molden created this paste up wolf art piece of a wolf surrounded by flies made by Zach Medler (ZMED). Paste ups are art pieces designed on paper, cut out, and glued to the wall with a mixture of flour and water. Because of it’s method of creation, the work will have a shorter lifespan than much of the other artwork as it will wear away over time as it is exposed to the natural elements. This illuminates the true impermanence of street art as in the future, it will likely degrade and be covered with a new piece. Medler’s flies show up in many places downtown, creating a theme which connects murals around Lafayette.\", \"geo_lat\":\"40.41983408\", \"geo_long\":\"-86.89125335\", \"id\":\"36\", \"image_location\":\"img_36.png\"}," +
            "" +
            "{\"artist\":\"Anonymous\",\"desc\":\"This isn’t an official Small Spaces piece, but it bridges the gap between the project and the community as a collaboration between Zach Medler (ZMED), and the Tippecanoe Arts Federation’s After School Art Program which aims to provide local youth with a positive expressive outlet. The many pieces that fall into this category show the project’s success at fostering the relationship between the city and its art scene. The vibrant colors, varied textures, and three-dimensional components set it apart from the majority of the Small Spaces project, yet still complement the energy and creativity present in the rest of the local art. The instruments remain intact, but weathering and aging of this piece connects the vitality of the art with the ruggedness of its environment. This development illustrates the impact of the space on the art while the art makes the place.\", \"geo_lat\":\"440.41936074\", \"geo_long\":\"-86.8913415\", \"id\":\"37\", \"image_location\":\"img_37.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"The Building you are facing is studded with various other hidden gems like these. You have to look up to see this piece. This two dimensional rabbit created on brick is another one of Aaron Bumgarner’s pieces. It is a contrasting image of a rabbit along with a pair of human hands. This piece is simple and abstract and therefore many interpretations can be derived from it. The rabbit piece along with the other beautiful pieces have completely changed the face of this building. These gems have brought the attention of passersby to this usually ignored space.\", \"geo_lat\":\"40.41931106\", \"geo_long\":\"-86.89075114\", \"id\":\"38\", \"image_location\":\"img_38.png\"}," +
            "" +
            "{\"artist\":\"Bethany Horman\",\"desc\":\"Bethany Horman created this piece that is located on the side of a building above a picture of some rabbits. It's a little above eye level, so look up and you'll find it. It's above a window and inside is a eating area that looks like a diner. Between the fun rabbits and the pink elephant is the perfect spot for kids. This piece also cleverly advertises a family-­friendly restaurant right below. If you look around on this wall of the building, you'll find more Small Spaces artwork.\", \"geo_lat\":\"40.4193507\", \"geo_long\":\"-86.8907269\", \"id\":\"39\", \"image_location\":\"img_39.png\"}," +
            "" +
            "{\"artist\":\"Cathryn English\",\"desc\":\"This is another Cathryn English piece. She was a local of Lafayette from 2007-2009, who now lives in West Lafayette, connected to Purdue University. The picture consists of three rabbits on the foreground and three holes in the background. The muted color scheme coupled with a rough texture makes this art of abstract type. No specific definition or theory can be derived from this highly abstract work of art. The beauty is that it lets your imagination run wild like a bunny in a garden. Pieces like these make the walls of Lafayette more interesting than ever. There are a couple of other rabbits around downtown Lafayette, maybe there’s a connection between these three rabbits and the others around downtown? Who knows?\", \"geo_lat\":\"40.41935359\", \"geo_long\":\"-86.89071334\", \"id\":\"40\", \"image_location\":\"img_40.png\"}," +
            "" +
            "{\"artist\":\"Mitch Schuring\",\"desc\":\"This piece, done by an artist by the name of Mitch Schuring, is on a boarded window above Zach Medler’s Studio. Many pieces were painted like this one to highlight modern technological dependency. Because it can be seen easily from the sidewalk across the street, the painting catches the attention of people who used to walk past the building without a second thought. Now they can glance up and see the artwork done by a member of their own town. These paintings were done by taking the board out of the window, painting over it, and then placing it back up. This process resembles that of a piece in a museum, not made in that place but put up later. However, it was made specifically for the slot in the window and because of that fits perfectly in the space. This piece in particular makes slight fun of texters by having the goofy cartoon with a big nose and funny shaped haircut. Notice how the character is standing and his posture. What does this make you think about excessive textures?\", \"geo_lat\":\"440.41940953\", \"geo_long\":\"-86.89066311\", \"id\":\"41\", \"image_location\":\"img_41.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This piece, titled “Birds on a Wire”, is yet another art piece created by Small Spaces organizer Zach Medler. It was created using paper that was pasted up on the wall using flour and water. This was done because it was inexpensive and easily accessible, and it has also created an interesting pattern of wear and tear on the piece. Zach placed this piece high up on a building in such a way that it would appear as though the pigeons were sitting on an overhanging wire if passerby were standing in the right place. This piece has created a small, interesting place by adding a quirky little piece of art that is simple, but uses visual tricks in the environment. This piece is so subtle, that some people may look up at it and not even realize that it is an art piece instead of actual pigeons sitting on the wire.\", \"geo_lat\":\"40.4193375\", \"geo_long\":\"-86.89062884\", \"id\":\"42\", \"image_location\":\"img_42.png\"}," +
            "" +
            "{\"artist\":\"Jonny Maj\",\"desc\":\"This hard-to-find piece bears very special significance to the artist, Jonny Maj. Growing up with his extended family, he based this painting on his grandmother’s favorite necklace. As opposed to some of the more noticeable and boisterous styles, Jonny Maj’s style is more subdued. His color palette is designed to give focus to the centerpiece of the painting: his grandmother’s necklace. While the colors are bright, they are not intended to grab your attention, making this work a little hard to find, but very hard to forget. Individual details on the painting are very difficult to discern due to the height at which the mural is placed and the small size of the mural itself, but the apparent randomness of the piece breaks through the monotony of busy streets of Lafayette, making it stand out from its surroundings.\", \"geo_lat\":\"40.4194506\", \"geo_long\":\"-86.89056788\", \"id\":\"43\", \"image_location\":\"img_43.png\"}," +
            "" +
            "{\"artist\":\"Joanne Titillo\",\"desc\":\"This piece was created by Joanne Titillo in conjunction with the piece above it: the bricks in the window. She was playing with the idea of bricks being above feathers, rather than the other way around, according to the weights of each. This continuity between art pieces creates an amusing effect that makes people think about the way art is portrayed typically. Another interesting thing about this art is that they are real feathers pasted on the inside of the glass for the door. This is a result of Zach encouraging artists to use nontraditional art mediums to diversify the art portfolio of Lafayette. The coolest aspect of this piece is the way it obscures what is through the window. If you press your face up to the glass of the window you might just be able to see what is inside.\", \"geo_lat\":\"40.41946044\", \"geo_long\":\"-86.89057083\", \"id\":\"44\", \"image_location\":\"img_44.png\"}," +
            "" +
            "{\"artist\":\"Joanne Titillo\",\"desc\":\"This piece was created by Joanne Titillo. This is one of many artworks located on this building. This piece may be difficult to find at first because it is located at the top row of windows on a three story building. It was created by going inside the building, taking the window out, painting it, and then putting it back in. This picture was made to relate to the feathers that are in the window of the door below the painting. It creates a paradox with the rocks up high and the feathers below. If you look closely enough you'll even see a feather in the rocks painting.\", \"geo_lat\":\"40.41951259\", \"geo_long\":\"-86.89059212\", \"id\":\"45\", \"image_location\":\"img_45.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"This simple yet clever sign was created by Aaron Bumgarner. His signs are posted on multiple telephone poles throughout town, so don’t forget to look up for art pieces around downtown Lafayette It is a unique piece simply because of its location. This art is visible while walking along the sidewalk, or driving down the street. It isn’t every day that you see art displayed on a telephone pole. This piece lends to the idea that Small Spaces is for everyone in the community, because of the phrase “I made this for you.” Each sign posted along the streets of Lafayette stand on their own but have a common theme of positive and encouraging phrases.\", \"geo_lat\":\"40.41964642\", \"geo_long\":\"-86.89061609\", \"id\":\"46\", \"image_location\":\"img_46.png\"}," +
            "" +
            "{\"artist\":\"Carrie Wing\",\"desc\":\"This bright art paste up was created by Carrie Wing. She used wheat pasting to put it up on the side of the Lafayette Theater. She graduated from Purdue University with degrees in Fine Arts and Art Education, and currently puts these to work teaching art at Jefferson High School. Note how the bricks seem to be fading through the paper. This is due to the wheat pasting she used. The art will continue to disintegrate due to the elements and this adds character to the piece. Additionally, it makes the piece appear to be part of the wall rather than on top of it. The piece almost looks like a collage, almost as if it is a convergence of several different people’s energies in one piece.\", \"geo_lat\":\"40.41962406\", \"geo_long\":\"-86.89057549\", \"id\":\"47\", \"image_location\":\"img_47.png\"}," +
            "" +
            "{\"artist\":\"Aaron Czernack\",\"desc\":\"This paste up was created by Aaron Czernack and it is located on the side of the Lafayette theater. There are also many other paste up art pieces on this same wall, but if you look closely you can tell that there are several different types of paper and glue used. The psychedelic nature and optical illusion effect of this piece distinguish it from many of the other pieces. Notice the two tears on the sides of the piece that appear to twist with the dots and the peeled edges that seem to move towards you and fade away in other spots. The futuristic look of this piece goes in the spirit of Small Spaces effort to rejuvenate the Lafayette area. The regularity of the dots on this piece suggests it may have been created digitally, showing stark contrast to most street art which is done by hand.\", \"geo_lat\":\"40.41962435\", \"geo_long\":\"-86.89058851\", \"id\":\"48\", \"image_location\":\"img_48.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler & students from Bauer Center's Summer Program\",\"desc\":\"The artwork was painted on the back of Lafayette theatre by Zach Medler and the kids from Bauer Center’s Summer Program. Medler grew up in Indianapolis and is a Purdue graduate. His work captures a variety of topics in a serious yet playful manner. This particular artwork is made of cartoons and printed graffiti which depicts the playful side of Medler and kids. According to you, what serious message are these post ups conveying? The post ups are put up in the alley way with several other paintings. They stand out because they are one of the few artworks that are black and white color while others are filled with vibrant colors.\", \"geo_lat\":\"40.41962986\", \"geo_long\":\"-86.89056107\", \"id\":\"49\", \"image_location\":\"img_49.png\"}," +
            "" +
            "{\"artist\":\"TAF Students\",\"desc\":\"As part of the Tippecanoe Art Federation, a group of students approached the director of Small Spaces, Zach Medler, and asked to add a piece to the project. Medler agreed, and with the help of artist Brandon Apitz, they created this stunning piece. The art is believed to be  inspired by a religious idea, with a cherub being a major component of the artwork. The size of the mural is significant for this piece. Not only is it one of the largest parts of Small Spaces, it covers a majority of the wall in the alley and dwarfs the paintings and paste-ups around it. Due to its location by the entrance to the alleyway, its size, and its vibrant colors, it is clearly visible from the road and easily captures the attention of walkers and drivers alike, drawing them into the alleyway to observe it and all the artwork surrounding it.\", \"geo_lat\":\"40.41962048\", \"geo_long\":\"-86.89050487\", \"id\":\"50\", \"image_location\":\"img_50.png\"}," +
            "" +
            "{\"artist\":\"Kevin Burdick\",\"desc\":\"The artist of this piece is Kevin Burdick. While not too much is known about him, he is one of Small Spaces’ most active artists. He’s painted several pieces in Lafayette, including a depiction of Clint Eastwood that is downtown. This piece is made in traditional, popular graffiti style, called Wildstyle. Wildstyle is an elaborate form of graffiti that uses intertwined and overlapping letters and shapes.  This piece is an illustration of the word ‘scraps’ and is made with a myriad of bright, striking colors. This piece is on the wall next to the back door of the Lafayette Theatre. What catches one’s eye about this piece is the way the colors, that ought to have jarred us, merge and flow with each other and give a feel of wet paint.\", \"geo_lat\":\"40.41962785\", \"geo_long\":\"-86.89047839\", \"id\":\"51\", \"image_location\":\"img_51.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"The artist of this piece, titled “Free thought. No change”, is Zach Medler, the curator of Small Spaces. It is located on the side of a building with many other murals in an alleyway. All of these murals and artworks put together transform the empty alley into a place to go out of your way to visit. The idea behind this piece is how free thought seems to be promoted, but nothing really makes an impact. Think about the ways that you can relate to this, and take a look at the other work on the wall to see how it impacts the space.\", \"geo_lat\":\"40.41963066\", \"geo_long\":\"-86.89046658\", \"id\":\"52\", \"image_location\":\"img_52.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This piece is titled “These Pundits Don’t Define Me (After David)” and was created by the curator of Small Spaces, Zach Medler (ZMED). It is based on a painting of Jean-Paul Marat, a French newspaper author during the French Revolution, who was stabbed in his bathtub for the articles he published. Zach is known for creating new, modern pieces from classic historical pieces, and felt this represented the backlash he received from the media negatively portraying Small Spaces. This mural is placed above several other pieces in an alley, and the eye is drawn to an array of brightly colored pieces below before reaching this piece above. Pay close attention to the writing on the paper the figure in the art is holding: it contains a twist on the historical artwork that is relevant to the Small Spaces controversy of today.\", \"geo_lat\":\"40.41963414\", \"geo_long\":\"-86.89044685\", \"id\":\"53\", \"image_location\":\"img_53.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"The artist of “Lunch Break” is Zach Medler, the creator of Small Spaces. Medler uses his thoughts on technology throughout multiple pieces of art, and it is displayed in this piece as well. By having a person in the doorway, this artwork makes an old, run down space feel like it’s occupied and vibrant again. When visiting the alley where this artwork is placed, you can see how the other artworks are colorful, large, fun and loud. This piece is simpler and more realistic than its neighbors, but is also all consuming. When you step in front of the artwork, you may feel calm or lonely. When looking closely, you can see the man is holding an electronic device as well as his lunch. \", \"geo_lat\":\"40.41963051\", \"geo_long\":\"-86.8904009\", \"id\":\"54\", \"image_location\":\"img_54.png\"}," +
            "" +
            "{\"artist\":\"TEAD & RAWR\",\"desc\":\"This piece of art was painted by TEAD and RAWR on the backside of Lafayette Theatre. They are famous street artists from Detroit, Michigan. They stopped by to paint this piece when they were on their way to Indianapolis to attend the SubSurface Graffiti event. This piece was one of the first in the project to involve graffiti writing. This art piece interacts very differently with its surrounding compared to the other neighboring artworks. This is mainly because of the bold writing and vibrant colors used. If you go to the end of alley way, it would be the first one to grab your attention, despite being located at the other end. The black structures in the background is something that stands out for this piece of artwork.\", \"geo_lat\":\"40.41963051\", \"geo_long\":\"--86.8904009\", \"id\":\"55\", \"image_location\":\"img_55.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This Zach Medler piece references the negative media attention that Small Spaces has experienced despite being welcomed and approved of by the general Lafayette community; his statement indirectly contests the negativity in a simple but creative way. This is one of the few pieces of art with plain formatting which relies on its content alone. The bold color and content draw attention to the art pieces located higher up in the alley. Its raised placement broadens the attraction of the space beyond eye-level and encourages people to interact with lesser-seen parts of the city.\", \"geo_lat\":\"40.41963051\", \"geo_long\":\"-86.8904009\", \"id\":\"56\", \"image_location\":\"img_56.png\"}," +
            "" +
            "{\"artist\":\"Craig Tribble\",\"desc\":\"The artist of this piece is 29 year old Craig Tribble, an alumnus of the Herron School of Art, Indianapolis, IN. who currently resides in West Lafayette. He presented his works at the Spectrum Gallery in Lafayette. This piece, titled “Strange Girls”, is present on the back wall of the Lafayette Brewing company next to another piece that says ‘I did it for the lulz’. It is placed below the ‘don’t trespass’ sign on a door and ironically draws people closer to inspect the door.  This piece stands out because of its eccentricity and peeling post-up paper which gives the piece an eerie feel.\", \"geo_lat\":\"40.41958423\", \"geo_long\":\"-86.88992114\", \"id\":\"57\", \"image_location\":\"img_57.png\"}," +
            "" +
            "{\"artist\":\"Craig Tribble\",\"desc\":\"Artist Craig Tribble created this piece of art titled, “I Did It for the LULZ.” The artist used not only spray paint, but also wheat paste-up. This medium is admired for its temporary qualities as it peels from surfaces and wears from the weather, which adds character over time. The title and the words painted alongside the paste-up, “I did it for the LULZ,” are a common pop culture saying, which can be used to reason doing just about anything these days, even if it makes you look like a pig in tighty-whities. While many Small Spaces pieces seem to completely transform a space by bringing in elements uncommonly seen in the downtown area to the space, this piece has grungy character, which suits the space it occupies. Even so, the piece is provocative and makes viewers think about the implications of doing things “for the LULZ.”\", \"geo_lat\":\"40.41961007\", \"geo_long\":\"-86.88990793\", \"id\":\"58\", \"image_location\":\"img_58.png\"}," +
            "" +
            "{\"artist\":\"Mitch Shuring\",\"desc\":\"This Mitch Shuring piece is unique in that it is semi-hidden. Tucked back in an alley, you have to look up to where you wouldn’t expect an art piece to be located. It is easily seen from the ground once you know to look up, but can also be reached by a set of stairs off the side of the building. Its location encourages adventurous viewers up the stairs to a fantastic view not only of the city, but of the hidden street art on top of other buildings. The Ghostbuster-like piece stands alone with no background on the building, and turns an alleyway into an art treasure hunt of sorts.\", \"geo_lat\":\"40.4195509\", \"geo_long\":\"-86.88988538\", \"id\":\"59\", \"image_location\":\"img_59.png\"}," +
            "" +
            "{\"artist\":\"James Werner & TAF Students\",\"desc\":\"This panoramic by is one of the most beautiful and intricate pieces from the Small Spaces project. It is a collaboration between James Werner, an award winning local artist and Education Committee Member of the Art Museum of Greater Lafayette, and a class from the Tippecanoe Arts Federation, called After School Arts Program (ASAP). The panoramic view of the two communities across the river is an interesting perspective as it drives both the feeling of separation by the river, and the unity created by the pedestrian bridge connecting Greater Lafayette. A big aspect of the Small Spaces project is bringing together the local community through art, and Werner’s piece certainly accomplishes this goal.\", \"geo_lat\":\"40.41943113\", \"geo_long\":\"-86.8894967\", \"id\":\"60\", \"image_location\":\"img_60.png\"}," +
            "" +
            "{\"artist\":\"Esteban Garcia\",\"desc\":\"Esteban Garcia and his crew put up this piece in 2014. The piece was a post-up of a very popular Lafayette based rock-n-roll band, The Minivans. It is located on the wall of the building which would be raucous, loud, and brimming with fans when the band used to perform there. The post-up is now peeling but it still marks the silhouette of The Minivans playing.\", \"geo_lat\":\"40.41963615\", \"geo_long\":\"-86.88926888\", \"id\":\"61\", \"image_location\":\"img_61.png\"}," +
            "" +
            "{\"artist\":\"Chris Tolliver\",\"desc\":\"This piece was created and installed by Chris Tolliver; it incorporates spray paint, string, and clay to create an overall organic and whimsical effect. This piece is actually one of the very few three dimensional pieces in Small Spaces. Since its creation, much of the string has either fallen off or otherwise worn away, but this only adds to the organic theme. Note the clay fungus throughout the piece, and the few remaining string sections which tiny, almost bridge-like structures between the clay fungi. The artwork extends to the innermost side of the wooden post on the left side enhancing the three-dimensional effect, almost as if the piece is a miniature landscape.\", \"geo_lat\":\"40.41963236\", \"geo_long\":\"-86.88913082\", \"id\":\"62\", \"image_location\":\"img_62.png\"}," +
            "" +
            "{\"artist\":\"Jenna Robinson\",\"desc\":\"This comical Jenna Robinson piece is stationed on a back alley fence deep within Lafayette. Robinson drew inspiration from her roommate’s cat and made her mark on Small Spaces with a quirky piece. It is done in a lively and eccentric pop art style similar to pieces by Andy Warhol. A favorite of Small Spaces curator Zach Medler, these cats are sure to brighten even the stormiest Indiana days. Robinson works at Purdue and serves as a Small Spaces ambassador; she is a part of the team of West Lafayette artists helping to bridge the gap between the students of Purdue and the residents of Lafayette.\", \"geo_lat\":\"40.41966283\", \"geo_long\":\"-86.8890671\", \"id\":\"63\", \"image_location\":\"img_63.png\"}," +
            "" +
            "{\"artist\":\"Anonymous\",\"desc\":\"This piece does not actually belong to the Small Spaces collection and has no recorded author. The rough texture and the pleasant color scheme of the painting on this door makes this piece unique and pleasant to look at. Passersby could spend hours looking at this simple yet deep painting. This work of art looks like a doorway to a paradise of green grass and green mountains with happy sheep. What once was a plain and boring door leading to some room now leads the imagination to this beautiful fertile land, like a gate between the concrete jungle of Downtown Lafayette to the untouched plains of Kashmir.\", \"geo_lat\":\"40.41941497\", \"geo_long\":\"-86.88824344\", \"id\":\"64\", \"image_location\":\"img_64.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgarner\",\"desc\":\"This Aaron Bumgarner piece is a part of a larger group of signs all over downtown. It is a sketch on wood bolted up high above traffic signs. While other signs around town have positive messages or compliments to the people of Lafayette or Small Spaces Lafayette itself, this one seems to signal the imminent presence of graffiti and art around the city of Lafayette. The Artist is trying to invoke the sense of smell by literally stating that the city smells ‘like paint’. This piece of art charges passersby to look out for pieces of art all around the place that have impacted spaces in Downtown Lafayette.\", \"geo_lat\":\"40.41963326\", \"geo_long\":\"-86.88822767\", \"id\":\"65\", \"image_location\":\"img_65.png\"}," +
            "" +
            "{\"artist\":\"Anonymous\",\"desc\":\"This small piece was done by an unknown artist who is not a part of Small Spaces. This artist has put up these face sketches in various places along the Small Spaces art work. This tiny piece of paper is hardly noticeable among the wall filled with huge paintings, but it brings character to the little metal piece on which it sits. It may be a small work of art, but it is largely significant in that it demonstrates how street art is a growing movement, and how Small Spaces has encouraged other local artists to put out their own street art. What does Small Spaces inspire you to make? Look around and see the other giant pieces of artwork surrounding this small little face. Why do you think the artist chose to make their piece so small?\", \"geo_lat\":\"40.41963326\", \"geo_long\":\"-86.88822767\", \"id\":\"66\", \"image_location\":\"img_66.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This mural is a collaboration between several artists including the lead art curator of the project, Zach Medler (ZMED). Zach enjoys painting his cat into his works; here, the cat can be found in the top right corner. The piece captures some memorable experiences shared by the artists of Small Spaces during the project. For example, the narwhal is climbing up the stairs holding a paint roller to represent how some of the artists’ works had to be buffed over. Many people ask Zach if the African American man painted in the bottom left corner is Obama; however, he claims it can be whoever the viewer wants it to be. The blue monkey and many birds can be seen hanging from a real wire, and the mural of the boy with a cape is trying to reach the top of the drain pipe. Interesting aspects like these redefine the alley it is in, making it a destination through its integration into physical elements of the space it occupies.\", \"geo_lat\":\"40.41954201\", \"geo_long\":\"-86.88520019\", \"id\":\"67\", \"image_location\":\"img_67.png\"}," +
            "" +
            "{\"artist\":\"Scott Frankenburger\",\"desc\":\"This artwork is by Scott Frankenburger, a pottery artist by profession. His studio is across the river in West Lafayette, and his work is generally used in homes and work spaces. This particular art piece is not a part of the Small Spaces project, but is significant for being one of his and Lafayette’s first pieces of public art. It pays tribute to Lafayette’s railroad history, standing just a feet away from where railroad tracks were located before Lafayette’s Railroad Relocation project finished in April 2001. This artwork is at the very end of Downtown, now acting as a gateway between the residential and commercial area. For more information, please refer to the plaque.\", \"geo_lat\":\"40.41908995\", \"geo_long\":\"-86.88483626\", \"id\":\"68\", \"image_location\":\"img_68.png\"}," +
            "" +
            "{\"artist\":\"Felix Maldonado\",\"desc\":\"One of the most notable pieces in the Small Spaces project is a large mural painted by Felix Maldonado. Known by the name Flex in the street art community, Maldonado is a well respected craftsman from Detroit, Michigan. His Small Spaces work resides next to the parking lot of a communications store. Contrasting with the technology sold next door, Maldonado’s work harkens back to a simple time. Referencing both the innocence of childhood, and a time before the technological takeover, Flex’s artwork appeals to viewers both visually and emotionally. Maldonado’s realistic style is also somewhat of a contrast to the rest of the Small Spaces project. While many of the pieces embody the graffiti style, Flex’s shading, reflections, and detailed textures are unmistakably unique amongst the other Small Spaces artworks.\", \"geo_lat\":\"40.41847363\", \"geo_long\":\"-86.88560146\", \"id\":\"69\", \"image_location\":\"img_69.png\"}," +
            "" +
            "{\"artist\":\"Dr. Goh Ban Eng & Zach Medler\",\"desc\":\"Dr. Goh Ban Eng and the creator of Small Spaces, ZMED, collaborated to create this large, beautiful mural which harmoniously combines both of the artists’ distinct artistic styles. Dr. Goh Ban Eng is the oldest artist to take part in Small Spaces. She comes from Singapore where graffiti and street art are highly illegal, and conviction of “vandalism” can land an artist in prison for up to three years with caning. Notice in the far left corner of the piece that water can be seen as gushing out of the rusty water pipe, which brings a powerful rural element into an urban  place. Facial features, especially eyes and mouths, are prevalent parts of this piece; animals, patterns, and trees are repeated elements as well. Each seemingly disconnected element of the painting attracts viewers’ attention towards nature and vibrancy in an area that would have previously been overlooked.\", \"geo_lat\":\"40.420307\", \"geo_long\":\"-86.891472\", \"id\":\"70\", \"image_location\":\"img_70.png\"}," +
            "" +
            "{\"artist\":\"Anonymous\",\"desc\":\"This Small Spaces mural enlightens the infrastructure with positive, encouraging messages and warm colors. It is a spray-painted collage of elements: a combination bold letters, geometric patterns, stenciled shapes, and contrasting colors. The simplistic cat and person further contrast an otherwise professional graffiti style with childish characteristics. This piece is unique, as it is one of the few with #SmallSpacesLafayette incorporated into the design.\", \"geo_lat\":\"440.41861111111111\", \"geo_long\":\"-86.88527777777779\", \"id\":\"72\", \"image_location\":\"img_72.png\"}," +
            "" +
            "{\"artist\":\"Temre Stanchfield\",\"desc\":\"The wall was elegantly patterned by Temre Stanchfield. After painting the colored foundation, she let her kids’ creativity run wild on top of it in black spray paint. The initial illustration reflects elements of the environment: the telephone wires interact with the wires on the wall, and the color palette and soft background complement the natural feel of the foliage on the ground. The combination of real and made up creatures in the spray painted additions parallel the delicate, distorted reality of the base.\", \"geo_lat\":\"40.41861111111111\", \"geo_long\":\"-86.88583333333334\", \"id\":\"73\", \"image_location\":\"img_74.png\"}," +
            "" +
            "{\"artist\":\"Mitch Shuring\",\"desc\":\"This Mitch Shuring piece features The Simpsons inspired characters, as stylized in many of his other works of art. He incorporates outside elements as well. One character appears to be leaning on the real telephone pole, and Shuring’s tag is included as the logo on the other character’s shirt. The modern clothes and nonchalant poses allow the art to liven and bring color to the space while maintaining an urban and inconspicuous vibe. The Small Spaces Lafayette hashtag is present here, continuing brand establishment and recognition.\", \"geo_lat\":\"40.418055555555554\", \"geo_long\":\"-86.88694444444445\", \"id\":\"74\", \"image_location\":\"img_75.png\"}," +
            "" +
            "{\"artist\":\"Zach Medler\",\"desc\":\"This is one of the few Small Spaces pieces for which the artist chooses to not conform the art to the architecture of the building. This robot by Zach Medler has been laying there reading for a while as made apparent by the bird perched on its leg, which juxtaposes the “No Parking” sign on the wall. The cat here is a robot-depiction of Medler’s own cat which he often features in his art.\", \"geo_lat\":\"40.41916666666666\", \"geo_long\":\"-86.88555555555556\", \"id\":\"75\", \"image_location\":\"img_76.png\"}]}";
    MapView mMapView;
    protected GoogleMap googleMap;
    //private ListView mDrawerList;
    private RelativeLayout mRLDrawer;
    private DrawerLayout mDrawerLayout;
    private Object mActivityTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> mAdapter;
    private JSONObject obj = null;
    private TextView tvName;
    private TextView tvDesc;
    private ImageView imageView;
    private Bundle mBundle;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        v = inflater.inflate(R.layout.map_fragment, container,
                false);
        tvName = (TextView) v.findViewById(R.id.tvName);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        imageView = (ImageView) v.findViewById(R.id.imageView);


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView = (MapView) v.findViewById(R.id.mapView);
        final Bundle mapViewSavedInstanceState = savedInstanceState != null ? savedInstanceState.getBundle("mapViewSaveState") : null;
        mMapView.onCreate(mapViewSavedInstanceState);
        //....
        setupMapIfNeeded(v);

        // latitude and longitude

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int id = Integer.parseInt(marker.getTitle());
                try {
                    tvName.setText(obj.getJSONArray("collection").getJSONObject(id).getString("artist"));
                    tvDesc.setText(obj.getJSONArray("collection").getJSONObject(id).getString("desc"));
                    imageView.setImageDrawable(getDrawableFromMarker(obj.getJSONArray("collection").getJSONObject(id).getString("id")));
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("MarkerClick", "Issue with marker JSON");
                }
                return true;
            }
        });
        Button b =(Button) v.findViewById(R.id.bToggle);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //mDrawerList = (ListView)v.findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)v.findViewById(R.id.drawer_layout);
        mRLDrawer = (RelativeLayout) v.findViewById(R.id.rlDrawer);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mActivityTitle = getActivity().getTitle().toString();

        addDrawerItems();
        setupDrawer();

        return v;
    }

    private void setupMapIfNeeded(View v) {
        if(googleMap==null){
            googleMap = ((MapView) v.findViewById(R.id.mapView)).getMap();
            if(googleMap!=null){
                addMarkers();
            }
        }

    }

    public void onSaveInstanceState(Bundle outState){
        //This MUST be done before saving any of your own or your base class's variables
        final Bundle mapViewSaveState = new Bundle(outState);
        mMapView.onSaveInstanceState(mapViewSaveState);
        outState.putBundle("mapViewSaveState", mapViewSaveState);
        //Add any other variables here.
        super.onSaveInstanceState(outState);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    private Drawable getDrawableFromMarker(String string) {
        int id = Integer.parseInt(string);
        switch(id){
            case 1:
                return getResources().getDrawable(R.drawable.n01);
            case 2:
                return getResources().getDrawable(R.drawable.n02);
            case 3:
                return getResources().getDrawable(R.drawable.n03);
            case 4:
                return getResources().getDrawable(R.drawable.n04);
            case 5:
                return getResources().getDrawable(R.drawable.n05);
            case 6:
                return getResources().getDrawable(R.drawable.n06);
            case 7:
                return getResources().getDrawable(R.drawable.n07);
            case 8:
                return getResources().getDrawable(R.drawable.n08);
            case 9:
                return getResources().getDrawable(R.drawable.n09);
            case 10:
                return getResources().getDrawable(R.drawable.n10);
            case 11:
                return getResources().getDrawable(R.drawable.n11);
            case 12:
                return getResources().getDrawable(R.drawable.n12);
            case 13:
                return getResources().getDrawable(R.drawable.n13);
            case 14:
                return getResources().getDrawable(R.drawable.n14);
            case 15:
                return getResources().getDrawable(R.drawable.n15);
            case 16:
                return getResources().getDrawable(R.drawable.n16);
            case 17:
                return getResources().getDrawable(R.drawable.n17);
            case 18:
                return getResources().getDrawable(R.drawable.n18);
            case 19:
                return getResources().getDrawable(R.drawable.n19);
            case 20:
                return getResources().getDrawable(R.drawable.n20);
            case 21:
                return getResources().getDrawable(R.drawable.n21);
            case 22:
                return getResources().getDrawable(R.drawable.n22);
            case 23:
                return getResources().getDrawable(R.drawable.n23);
            case 24:
                return getResources().getDrawable(R.drawable.n24);
            case 25:
                return getResources().getDrawable(R.drawable.n25);
            case 26:
                return getResources().getDrawable(R.drawable.n26);
            case 27:
                return getResources().getDrawable(R.drawable.n27);
            case 28:
                return getResources().getDrawable(R.drawable.n28);
            case 29:
                return getResources().getDrawable(R.drawable.n29);
            case 30:
                return getResources().getDrawable(R.drawable.n30);
            case 31:
                return getResources().getDrawable(R.drawable.n31);
            case 32:
                return getResources().getDrawable(R.drawable.n32);
            case 33:
                return getResources().getDrawable(R.drawable.n33);
            case 34:
                return getResources().getDrawable(R.drawable.n34);
            case 35:
                return getResources().getDrawable(R.drawable.n35);
            case 36:
                return getResources().getDrawable(R.drawable.n36);
            case 37:
                return getResources().getDrawable(R.drawable.n37);
            case 38:
                return getResources().getDrawable(R.drawable.n38);
            case 39:
                return getResources().getDrawable(R.drawable.n39);
            case 40:
                return getResources().getDrawable(R.drawable.n40);
            case 41:
                return getResources().getDrawable(R.drawable.n41);
            case 42:
                return getResources().getDrawable(R.drawable.n42);
            case 43:
                return getResources().getDrawable(R.drawable.n43);
            case 44:
                return getResources().getDrawable(R.drawable.n44);
            case 45:
                return getResources().getDrawable(R.drawable.n45);
            case 46:
                return getResources().getDrawable(R.drawable.n46);
            case 47:
                return getResources().getDrawable(R.drawable.n47);
            case 48:
                return getResources().getDrawable(R.drawable.n48);
            case 49:
                return getResources().getDrawable(R.drawable.n49);
            case 50:
                return getResources().getDrawable(R.drawable.n50);
            case 51:
                return getResources().getDrawable(R.drawable.n51);
            case 52:
                return getResources().getDrawable(R.drawable.n52);
            case 53:
                return getResources().getDrawable(R.drawable.n53);
            case 54:
                return getResources().getDrawable(R.drawable.n54);
            case 55:
                return getResources().getDrawable(R.drawable.n55);
            case 56:
                return getResources().getDrawable(R.drawable.n56);
            case 57:
                return getResources().getDrawable(R.drawable.n57);
            case 58:
                return getResources().getDrawable(R.drawable.n58);
            case 59:
                return getResources().getDrawable(R.drawable.n59);
            case 60:
                return getResources().getDrawable(R.drawable.n60);
            case 61:
                return getResources().getDrawable(R.drawable.n61);
            case 62:
                return getResources().getDrawable(R.drawable.n62);
            case 63:
                return getResources().getDrawable(R.drawable.n63);
            case 64:
                return getResources().getDrawable(R.drawable.n64);
            case 65:
                return getResources().getDrawable(R.drawable.n65);
            case 66:
                return getResources().getDrawable(R.drawable.n66);
            case 67:
                return getResources().getDrawable(R.drawable.n67);
            case 68:
                return getResources().getDrawable(R.drawable.n68);
            case 69:
                return getResources().getDrawable(R.drawable.n69);
            case 70:
                return getResources().getDrawable(R.drawable.n70);
//            case 71:
//                return getResources().getDrawable(R.drawable.n71);
            case 72:
                return getResources().getDrawable(R.drawable.n72);
            case 73:
                return getResources().getDrawable(R.drawable.n73);
            case 74:
                return getResources().getDrawable(R.drawable.n74);
            case 75:
                return getResources().getDrawable(R.drawable.n75);
            case 76:
                return getResources().getDrawable(R.drawable.n76);
            case 77:
                return getResources().getDrawable(R.drawable.n77);
            case 78:
                return getResources().getDrawable(R.drawable.n78);
            case 79:
                return getResources().getDrawable(R.drawable.n79);

        }
        return null;
    }


    private void setupDrawer() {


        //mDrawerToggle.setDrawerIndicatorEnabled(true);

    }

    private void addDrawerItems() {
//        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
//        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, osArray);
//        mDrawerList.setAdapter(mAdapter);
//
//        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Time for an upgrade!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void addMarkers() {

        //TODO add Marker imports here
        int i=-1;
        try {
            obj = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BackgroundMarker","Error in writing JSON");
        }
        Log.d("BackgroundMarker", s);
        try {
            JSONArray arr = obj.getJSONArray("collection");

            for(i=0; i<arr.length();i++){
                JSONObject object = arr.getJSONObject(i);
                double lat = Double.parseDouble(object.getString("geo_lat"));
                double lng = Double.parseDouble(object.getString("geo_long"));
                MarkerOptions a = new MarkerOptions()
                        .position(new LatLng(lat,lng)).title(object.getString("id"));
                Marker m = googleMap.addMarker(a);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BackgroundMarker", "Error in getting JSONArray element "+i);
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(40.42, -86.89)).zoom(14).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

}