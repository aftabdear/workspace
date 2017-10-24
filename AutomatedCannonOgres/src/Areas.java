import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Vector3D;

public class Areas {
	//MINING POSITION STORING
//		public static int storeMiningPos1_1 = 0;
//		public static int storeMiningPos1_2 = 0;
//		public static int storeMiningPos1_3 = 0;
	
	
	public static Area cannonOgreaArea =  new Area(
		    new int[][]{
		        { 2533, 3381 },
		        { 2517, 3381 },
		        { 2517, 3367 },
		        { 2521, 3363 },
		        { 2527, 3363 },
		        { 2529, 3363 },
		        { 2534, 3368 },
		        { 2534, 3380 }
		    }
		);
	
	public static Area ardyTeleportArea = new Area(2647, 3322, 2676, 3290);
	
	public static Area SlayerMasterArea = new Area(3144, 9914, 3148, 9911);

	public static Area EdgevilleGloryArea = new Area(3072, 3506, 3103, 3485);
	public static Area BA = new Area(2894, 3559, 2903, 3546);
	public static Area TuraelArea = new Area(2930, 3538, 2933, 3535);
	public static Area VarrockArea = new Area(3039, 3515, 3293, 3349);
	public static Area GEArea = new Area(3161, 3493, 3167, 3486);
	public static Area FaldorCowArea = new Area(
			new int[][] { { 2913, 3290 }, { 2914, 3290 }, { 2915, 3291 }, { 2915, 3292 }, { 2920, 3292 },
					{ 2921, 3290 }, { 2926, 3290 }, { 2927, 3287 }, { 2929, 3284 }, { 2929, 3281 }, { 2927, 3277 },
					{ 2927, 3274 }, { 2929, 3274 }, { 2930, 3274 }, { 2932, 3277 }, { 2933, 3278 }, { 2936, 3278 },
					{ 2938, 3275 }, { 2938, 3273 }, { 2936, 3271 }, { 2936, 3270 }, { 2935, 3269 }, { 2933, 3268 },
					{ 2932, 3267 }, { 2928, 3267 }, { 2926, 3269 }, { 2924, 3269 }, { 2922, 3271 }, { 2922, 3274 } });

	public static Area FaladorHobgoblinArea = new Area(new int[][] { { 2921, 3275 }, { 2922, 3274 }, { 2922, 3271 },
			{ 2924, 3269 }, { 2926, 3269 }, { 2928, 3267 }, { 2933, 3267 }, { 2930, 3274 }, { 2928, 3275 } });

	public static Area KaramjaGloryArea = new Area(new int[][] { { 2916, 3178 }, { 2918, 3178 }, { 2920, 3178 },
			{ 2923, 3177 }, { 2927, 3175 }, { 2931, 3167 }, { 2933, 3165 }, { 2937, 3161 }, { 2935, 3158 },
			{ 2935, 3152 }, { 2917, 3154 }, { 2905, 3160 }, { 2907, 3168 } });

	public static Area GroundFloor = new Area(2806, 3124, 2813, 3119);

	public static Area PotholeDungeon = new Area(2818, 9534, 2879, 9473);

	public static Area RockArea = new Area(new int[][] { { 2819, 3120 }, { 2821, 3118 }, { 2822, 3117 }, { 2823, 3116 },
			{ 2824, 3116 }, { 2826, 3116 }, { 2827, 3117 }, { 2827, 3119 }, { 2827, 3120 }, { 2827, 3121 },
			{ 2825, 3122 }, { 2824, 3123 }, { 2823, 3124 }, { 2822, 3125 }, { 2822, 3126 }, { 2823, 3127 },
			{ 2824, 3128 }, { 2822, 3129 }, { 2819, 3128 }, { 2818, 3127 }, { 2817, 3125 }, { 2817, 3122 } });

	public static Area BrimhavenDungeonEntrance = new Area(2743, 3154, 2747, 3149);

	public static Area insideOfDungeon = new Area(2626, 9603, 2753, 9407);

	public static Area insideOfDungeonZ2 = new Area(
		    new int[][]{
		        { 2646, 9600 },
		        { 2647, 9588 },
		        { 2643, 9574 },
		        { 2643, 9570 },
		        { 2646, 9558 },
		        { 2628, 9560 },
		        { 2623, 9591 },
		        { 2633, 9599 }
		    }
		).setPlane(2);
	
	public static Area babyDragonArea = new Area(
		    new int[][]{
		        { 2640, 9576 },
		        { 2639, 9555 },
		        { 2651, 9555 },
		        { 2686, 9567 },
		        { 2675, 9585 },
		        { 2664, 9588 },
		        { 2656, 9586 }
		    }
		).setPlane(2);

	public static Position a = new Position(2691, 9564, 0);

	public static Area aArea = new Area(2693, 9575, 2714, 9557);

	public static Position a2 = new Position(2689, 9564, 0);

	public static Area bArea = new Area(2689, 9568, 2678, 9561);

	public static Position b1 = new Position(2683, 9568, 0);

	public static Position b2 = new Position(2683, 9570, 0);

	public static Area cArea = new Area(2686, 9570, 2677, 9583);

	public static Area c1 = new Area(2650, 9591, 2649, 9591);

	public static Area c2 = new Area(2643, 9595, 2643, 9594).setPlane(2);
	
	public static Position c3 = new Position(2652, 9569, 2);
	
}
