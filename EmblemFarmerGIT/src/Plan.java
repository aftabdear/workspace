import org.osbot.rs07.api.ui.RS2Widget;

public class Plan {

}


/*
*  The person De- equiping is the ByStander
*  The person attacking is the Killer
*  
*  
*  How the player decides who the killer and who the bystander is-
*  -First checks inventory to see who has higher level emblem
*  -Sends the level of their Emblem
*  -If same level emblem number is sent between 1 and 10000000, whoever has the higher number becomes the attacker
*  -If set as BYSTNADER player tele's via glory to edge and quickly de-equips and runs to area 
*  -IF set as ATTACKER player waits at area for bystander
* 
* 
* 
* ATTACKER - send area he is in to the BYSTANDER
* 		   - wait to be under attack / check if underattack is correct player 
* 		   - if underattack by a different player run south 
* 
* 
* 
* 
* 
* 
* 
* 
* 
*
*
*
*
*
*
*/
//
//script.log("pending options");
//yes.interact();
//RS2Widget yes = script.getWidgets().get(219, 0, 1);