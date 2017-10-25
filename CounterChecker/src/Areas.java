import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Vector3D;

public class Areas {
	public static Area lumbridgeFishingSpotFailSafe2 = new Area(3240, 3250, 3241, 3249);
	
	public static Area depositBoxArea = new Area(3043, 3237, 3052, 3234);
	
	public static Area lumbridgeFishingSpotFailSafe = new Area(3242, 3149, 3239, 3150);
	
	public static Area lumbridgeFishingSpot2 = new Area(3237, 3255, 3243, 3234);
	
	public static Position[] pathFishingSpot1ToFishingSpot2 = {
		    new Position(3242, 3150, 0),
		    new Position(3243, 3157, 0),
		    new Position(3243, 3167, 0),
		    new Position(3243, 3177, 0),
		    new Position(3243, 3187, 0),
		    new Position(3244, 3190, 0),
		    new Position(3239, 3199, 0),
		    new Position(3238, 3201, 0),
		    new Position(3235, 3206, 0),
		    new Position(3235, 3216, 0),
		    new Position(3235, 3225, 0),
		    new Position(3245, 3226, 0),
		    new Position(3254, 3226, 0),
		    new Position(3258, 3232, 0),
		    new Position(3249, 3237, 0),
		    new Position(3241, 3242, 0),
		    new Position(3240, 3247, 0)
		};
	
	public static Position[] pathLumbyTelespotToFishingSpot1 = {
		    new Position(3218, 3218, 0),
		    new Position(3226, 3218, 0),
		    new Position(3233, 3218, 0),
		    new Position(3234, 3210, 0),
		    new Position(3236, 3201, 0),
		    new Position(3244, 3196, 0),
		    new Position(3243, 3187, 0),
		    new Position(3243, 3177, 0),
		    new Position(3243, 3167, 0),
		    new Position(3243, 3157, 0),
		    new Position(3242, 3150, 0)
		};
	
	public static Area lumbridgeFishingSpot = new Area(3237, 3159, 3247, 3141);
	
	public static Area lumbridgeTeleSpot = new Area(3217, 3225, 3225, 3211);
	
	public static Area zoomedOutLumbridgeArea = new Area(
		    new int[][]{
		        { 3204, 3231 },
		        { 3204, 3229 },
		        { 3205, 3228 },
		        { 3205, 3227 },
		        { 3213, 3227 },
		        { 3213, 3212 },
		        { 3205, 3212 },
		        { 3205, 3210 },
		        { 3204, 3209 },
		        { 3204, 3207 },
		        { 3206, 3207 },
		        { 3207, 3208 },
		        { 3207, 3209 },
		        { 3215, 3209 },
		        { 3217, 3211 },
		        { 3217, 3227 },
		        { 3215, 3229 },
		        { 3207, 3229 },
		        { 3207, 3230 },
		        { 3206, 3231 }
		    }
		);

	public static Area failSafe1 = new Area(
			new int[][] { { 3012, 3359 }, { 3012, 3361 }, { 2997, 3360 }, { 2997, 3355 }, { 3000, 3352 },
					{ 3004, 3349 }, { 3005, 3345 }, { 3005, 3338 }, { 3003, 3335 }, { 3003, 3331 }, { 3003, 3327 },
					{ 3004, 3324 }, { 3005, 3324 }, { 3005, 3323 }, { 3005, 3322 }, { 3005, 3317 }, { 3002, 3310 },
					{ 3001, 3303 }, { 2999, 3295 }, { 2994, 3284 }, { 2993, 3277 }, { 2991, 3270 }, { 2986, 3262 },
					{ 2980, 3256 }, { 2979, 3252 }, { 2984, 3252 }, { 2985, 3251 }, { 2985, 3250 }, { 2986, 3250 },
					{ 2988, 3248 }, { 2988, 3247 }, { 2989, 3246 }, { 2990, 3246 }, { 2991, 3245 }, { 2991, 3238 },
					{ 2990, 3237 }, { 2990, 3234 }, { 2989, 3232 }, { 2988, 3231 }, { 2988, 3230 }, { 2986, 3228 },
					{ 2985, 3228 }, { 2984, 3227 }, { 2977, 3227 }, { 2976, 3228 }, { 2975, 3228 }, { 2974, 3229 },
					{ 2972, 3229 }, { 2970, 3226 }, { 2972, 3224 }, { 2975, 3223 }, { 2978, 3223 }, { 2983, 3223 },
					{ 2986, 3223 }, { 2991, 3226 }, { 2992, 3230 }, { 2994, 3233 }, { 2995, 3238 }, { 2996, 3244 },
					{ 2995, 3248 }, { 2991, 3252 }, { 2989, 3255 }, { 2990, 3259 }, { 2994, 3265 }, { 2998, 3274 },
					{ 3002, 3281 }, { 3005, 3289 }, { 3007, 3294 }, { 3009, 3312 }, { 3009, 3323 }, { 3009, 3335 },
					{ 3009, 3343 }, { 3012, 3350 }, { 3012, 3353 }, { 3009, 3353 }, { 3009, 3359 } });

	public static Area failSafe2 = new Area(new int[][] { { 3005, 3323 }, { 3004, 3318 }, { 3003, 3313 },
			{ 3002, 3304 }, { 3000, 3287 }, { 2995, 3284 }, { 2995, 3280 }, { 2991, 3271 }, { 2986, 3263 },
			{ 2981, 3256 }, { 2979, 3252 }, { 2975, 3252 }, { 2973, 3252 }, { 2972, 3251 }, { 2971, 3251 },
			{ 2970, 3249 }, { 2968, 3247 }, { 2967, 3247 }, { 2966, 3246 }, { 2966, 3245 }, { 2965, 3244 },
			{ 2964, 3242 }, { 2964, 3237 }, { 2965, 3236 }, { 2965, 3235 }, { 2968, 3232 }, { 2969, 3232 },
			{ 2970, 3231 }, { 2970, 3229 }, { 2972, 3229 }, { 2966, 3226 }, { 2964, 3228 }, { 2961, 3231 },
			{ 2960, 3235 }, { 2958, 3240 }, { 2959, 3247 }, { 2976, 3279 }, { 2995, 3316 }, { 3002, 3323 } });

	public static Area failSafe3 = new Area(
			new int[][] { { 3012, 3353 }, { 3011, 3345 }, { 3013, 3347 }, { 3015, 3347 }, { 3015, 3348 },
					{ 3016, 3348 }, { 3016, 3350 }, { 3023, 3350 }, { 3025, 3350 }, { 3026, 3352 }, { 3026, 3355 },
					{ 3026, 3356 }, { 3025, 3359 }, { 3024, 3361 }, { 3022, 3363 }, { 3019, 3364 }, { 3014, 3363 },
					{ 3012, 3363 }, { 3012, 3359 }, { 3019, 3359 }, { 3019, 3356 }, { 3022, 3356 }, { 3022, 3353 } });

	// MINING POSITION
	public static Position miningPos1 = new Position(Main.storeMiningPos1_1, Main.storeMiningPos1_2,
			Main.storeMiningPos1_3);
	public static Position miningPos2 = new Position(Main.storeMiningPos2_1, Main.storeMiningPos2_2,
			Main.storeMiningPos2_3);

	public static Area copperOreArea = new Area(2980, 3249, 2976, 3244);

	public static Area rimmingtonMiningArea = new Area(new int[][] { { 2970, 3248 }, { 2965, 3243 }, { 2965, 3236 },
			{ 2968, 3232 }, { 2969, 3232 }, { 2974, 3229 }, { 2983, 3228 }, { 2988, 3233 }, { 2990, 3239 },
			{ 2990, 3244 }, { 2985, 3248 }, { 2982, 3250 }, { 2973, 3250 } });

	public static Area coolDownArea = new Area(new int[][] { { 3215, 3242 }, { 3214, 3238 }, { 3216, 3236 },
			{ 3221, 3235 }, { 3227, 3229 }, { 3229, 3227 }, { 3232, 3225 }, { 3232, 3222 }, { 3236, 3222 },
			{ 3236, 3228 }, { 3238, 3228 }, { 3238, 3230 }, { 3237, 3233 }, { 3236, 3239 }, { 3235, 3243 } });

	// LUMBRIDGE
	public static Area lumbridgeTowerFloor1 = new Area(3227, 3225, 3230, 3221).setPlane(1);
	public static Area lumbridgeTowerFloor0 = new Area(3226, 3226, 3231, 3220);
	public static Area lumbridgeStairsFloor0 = new Area(3201, 3212, 3208, 3204);

	public static Area lumbridgeStairsFloor1 = new Area(new int[][] { { 3205, 3213 }, { 3208, 3213 }, { 3208, 3209 },
			{ 3207, 3209 }, { 3207, 3208 }, { 3206, 3207 }, { 3204, 3207 }, { 3204, 3209 }, { 3205, 3210 } })
					.setPlane(1);

	public static Area zoomedOutGDArea = new Area(2943, 3518, 2972, 3489); // oh
																			// fuck
																			// already
																			// go
																			// tit

	public static Area lumbridgeStairsFloor2 = new Area(new int[][] { { 3205, 3213 }, { 3208, 3213 }, { 3208, 3209 },
			{ 3207, 3209 }, { 3207, 3208 }, { 3206, 3207 }, { 3204, 3207 }, { 3204, 3209 }, { 3205, 3210 } })
					.setPlane(2);

	public static Area lumbridgeStairsFloor0North = new Area(3201, 3234, 3209, 3227);

	public static Area lumbridgeStairsFloor1North = new Area(
			new int[][] { { 3204, 3231 }, { 3206, 3231 }, { 3207, 3230 }, { 3207, 3229 }, { 3208, 3229 },
					{ 3208, 3226 }, { 3205, 3226 }, { 3205, 3228 }, { 3204, 3229 } }).setPlane(1);

	public static Area lumbridgeStairsFloor2North = new Area(
			new int[][] { { 3204, 3231 }, { 3206, 3231 }, { 3207, 3230 }, { 3207, 3229 }, { 3208, 3229 },
					{ 3208, 3226 }, { 3205, 3226 }, { 3205, 3228 }, { 3204, 3229 } }).setPlane(2);

	public static Area lumbridgeBank = new Area(3206, 3224, 3213, 3218).setPlane(2);

	public static Area CA = new Area(3205, 3217, 3212, 3212);

	public static Area DQAreaZoomedOut = new Area(2945, 3456, 2955, 3447);

	public static Area DQArea = new Area(2953, 3449, 2950, 3452);

	public static Area secretArea = new Area(3225, 3227, 3232, 3219).setPlane(1);

	public static Area GDArea = new Area(new int[][] { { 2954, 3513 }, { 2954, 3510 }, { 2962, 3510 }, { 2962, 3514 },
			{ 2959, 3514 }, { 2959, 3515 }, { 2956, 3515 }, { 2956, 3513 } });

	public static Area outsideLumbridgeTowerStairs = new Area(3221, 3225, 3230, 3220);

	public static Position[] pathToSecretRoom = { new Position(3231, 3233, 0), new Position(3232, 3223, 0),
			new Position(3232, 3223, 0), new Position(3227, 3218, 0), new Position(3226, 3223, 0) };

	// Organise this class later too xd

	// FARMING PATHS
	public static Position[] pathGDToFallyBank = { //
			new Position(2956, 3507, 0), new Position(2956, 3497, 0), new Position(2956, 3496, 0),
			new Position(2955, 3486, 0), new Position(2955, 3485, 0), new Position(2953, 3475, 0),
			new Position(2952, 3473, 0), new Position(2950, 3463, 0), new Position(2950, 3462, 0),
			new Position(2947, 3452, 0), new Position(2946, 3449, 0), new Position(2947, 3439, 0),
			new Position(2948, 3432, 0), new Position(2953, 3423, 0), new Position(2957, 3417, 0),
			new Position(2963, 3409, 0), new Position(2966, 3405, 0), new Position(2966, 3395, 0),
			new Position(2966, 3388, 0), new Position(2970, 3379, 0), new Position(2970, 3378, 0),
			new Position(2980, 3375, 0), new Position(2980, 3375, 0), new Position(2989, 3371, 0),
			new Position(2992, 3369, 0), new Position(3001, 3365, 0), new Position(3007, 3362, 0),
			new Position(3013, 3359, 0), new Position(3013, 3356, 0) };

	public static Position[] pathBankToMine1 = { new Position(3012, 3356, 0), new Position(3011, 3360, 0),
			new Position(3006, 3359, 0), new Position(3006, 3349, 0), new Position(3006, 3339, 0),
			new Position(3007, 3333, 0), new Position(3006, 3323, 0), new Position(3005, 3313, 0),
			new Position(3004, 3303, 0), new Position(3003, 3293, 0), new Position(3002, 3293, 0),
			new Position(2997, 3285, 0), new Position(2995, 3282, 0), new Position(2994, 3272, 0),
			new Position(2993, 3268, 0), new Position(2986, 3261, 0), new Position(2979, 3254, 0),
			new Position(2978, 3253, 0), new Position(2977, 3243, 0), new Position(2977, 3239, 0) };

	public static Position[] pathBankToMine2 = { new Position(3015, 3361, 0), new Position(3022, 3359, 0),
			new Position(3022, 3350, 0), new Position(3014, 3350, 0), new Position(3008, 3344, 0),
			new Position(3007, 3334, 0), new Position(3007, 3333, 0), new Position(3006, 3323, 0),
			new Position(3005, 3313, 0), new Position(3004, 3305, 0), new Position(2997, 3298, 0),
			new Position(2990, 3291, 0), new Position(2988, 3290, 0), new Position(2984, 3281, 0),
			new Position(2982, 3277, 0), new Position(2980, 3267, 0), new Position(2978, 3260, 0),
			new Position(2978, 3250, 0), new Position(2978, 3247, 0), new Position(2977, 3239, 0) };

	public static Position[] pathBankToMine3 = { new Position(3012, 3359, 0), new Position(3017, 3359, 0),
			new Position(3022, 3357, 0), new Position(3022, 3351, 0), new Position(3012, 3350, 0),
			new Position(3007, 3350, 0), new Position(3007, 3342, 0), new Position(3008, 3332, 0),
			new Position(3008, 3332, 0), new Position(3004, 3326, 0), new Position(3007, 3320, 0),
			new Position(3003, 3311, 0), new Position(3000, 3302, 0), new Position(2999, 3292, 0),
			new Position(2998, 3282, 0), new Position(2997, 3272, 0), new Position(2998, 3271, 0),
			new Position(2997, 3261, 0), new Position(2996, 3251, 0), new Position(2995, 3241, 0),
			new Position(2993, 3235, 0), new Position(2985, 3231, 0), new Position(2977, 3238, 0),
			new Position(2977, 3238, 0) };

	public static Position[] pathBankToMine4 = { new Position(3012, 3360, 0), new Position(3002, 3357, 0),
			new Position(3001, 3357, 0), new Position(3004, 3350, 0), new Position(3009, 3346, 0),
			new Position(3007, 3337, 0), new Position(3006, 3327, 0), new Position(3006, 3323, 0),
			new Position(3000, 3315, 0), new Position(2999, 3314, 0), new Position(2992, 3306, 0),
			new Position(2985, 3298, 0), new Position(2985, 3298, 0), new Position(2979, 3290, 0),
			new Position(2973, 3282, 0), new Position(2977, 3278, 0), new Position(2973, 3269, 0),
			new Position(2969, 3260, 0), new Position(2965, 3251, 0), new Position(2961, 3242, 0),
			new Position(2962, 3232, 0), new Position(2962, 3231, 0), new Position(2969, 3227, 0),
			new Position(2975, 3235, 0), new Position(2977, 3238, 0) };

	public static Position[] pathBankToMine5 = { new Position(3012, 3360, 0), new Position(3006, 3359, 0),
			new Position(3008, 3349, 0), new Position(3009, 3344, 0), new Position(3006, 3339, 0),
			new Position(3007, 3329, 0), new Position(3007, 3324, 0), new Position(3005, 3314, 0),
			new Position(3003, 3304, 0), new Position(3001, 3294, 0), new Position(2999, 3284, 0),
			new Position(2997, 3274, 0), new Position(2995, 3264, 0), new Position(2993, 3254, 0),
			new Position(2995, 3244, 0), new Position(2994, 3239, 0), new Position(2991, 3229, 0),
			new Position(2991, 3228, 0), new Position(2985, 3223, 0), new Position(2983, 3225, 0),
			new Position(2982, 3230, 0), new Position(2977, 3239, 0), new Position(2977, 3239, 0) };

	public static Position[] pathMineToBank1 = { new Position(2976, 3240, 0), new Position(2976, 3250, 0),
			new Position(2976, 3256, 0), new Position(2978, 3266, 0), new Position(2980, 3276, 0),
			new Position(2981, 3276, 0), new Position(2983, 3286, 0), new Position(2985, 3296, 0),
			new Position(2987, 3306, 0), new Position(2987, 3306, 0), new Position(2995, 3312, 0),
			new Position(3003, 3318, 0), new Position(3005, 3321, 0), new Position(3006, 3330, 0),
			new Position(3007, 3338, 0), new Position(3007, 3348, 0), new Position(3007, 3358, 0),
			new Position(3007, 3360, 0), new Position(3012, 3359, 0), new Position(3011, 3355, 0) };

	public static Position[] pathMineToBank2 = { new Position(2977, 3240, 0), new Position(2977, 3250, 0),
			new Position(2977, 3252, 0), new Position(2979, 3262, 0), new Position(2981, 3272, 0),
			new Position(2981, 3276, 0), new Position(2983, 3286, 0), new Position(2984, 3288, 0),
			new Position(2990, 3296, 0), new Position(2996, 3303, 0), new Position(3001, 3312, 0),
			new Position(3006, 3321, 0), new Position(3006, 3322, 0), new Position(3007, 3332, 0),
			new Position(3008, 3342, 0), new Position(3008, 3347, 0), new Position(3017, 3351, 0),
			new Position(3023, 3351, 0), new Position(3021, 3360, 0), new Position(3013, 3360, 0),
			new Position(3012, 3355, 0) };

	public static Position[] pathMineToBank3 = { new Position(2976, 3240, 0), new Position(2972, 3231, 0),
			new Position(2968, 3227, 0), new Position(2963, 3235, 0), new Position(2961, 3238, 0),
			new Position(2966, 3247, 0), new Position(2971, 3256, 0), new Position(2976, 3265, 0),
			new Position(2981, 3274, 0), new Position(2983, 3275, 0), new Position(2990, 3278, 0),
			new Position(2997, 3285, 0), new Position(3004, 3292, 0), new Position(3007, 3296, 0),
			new Position(3007, 3306, 0), new Position(3007, 3316, 0), new Position(3007, 3326, 0),
			new Position(3007, 3336, 0), new Position(3007, 3346, 0), new Position(3007, 3353, 0),
			new Position(3007, 3360, 0), new Position(3013, 3359, 0), new Position(3011, 3355, 0) };

	public static Position[] pathMineToBank4 = { new Position(2977, 3239, 0), new Position(2985, 3233, 0),
			new Position(2987, 3232, 0), new Position(2991, 3232, 0), new Position(2992, 3242, 0),
			new Position(2993, 3252, 0), new Position(2993, 3255, 0), new Position(2994, 3265, 0),
			new Position(2995, 3275, 0), new Position(2995, 3282, 0), new Position(2998, 3292, 0),
			new Position(3001, 3302, 0), new Position(3004, 3312, 0), new Position(3007, 3322, 0),
			new Position(3007, 3322, 0), new Position(3007, 3332, 0), new Position(3007, 3342, 0),
			new Position(3008, 3348, 0), new Position(3018, 3351, 0), new Position(3021, 3352, 0),
			new Position(3022, 3358, 0), new Position(3013, 3359, 0), new Position(3010, 3355, 0) };

	public static Position[] pathMineToBank5 = { new Position(2977, 3239, 0), new Position(2981, 3230, 0),
			new Position(2983, 3227, 0), new Position(2984, 3224, 0), new Position(2991, 3231, 0),
			new Position(2992, 3232, 0), new Position(2993, 3242, 0), new Position(2994, 3252, 0),
			new Position(2995, 3262, 0), new Position(2994, 3272, 0), new Position(2995, 3282, 0),
			new Position(2995, 3282, 0), new Position(3001, 3290, 0), new Position(3006, 3296, 0),
			new Position(3003, 3303, 0), new Position(3006, 3313, 0), new Position(3007, 3316, 0),
			new Position(3007, 3326, 0), new Position(3007, 3336, 0), new Position(3007, 3339, 0),
			new Position(3007, 3349, 0), new Position(3007, 3359, 0), new Position(3007, 3361, 0),
			new Position(3012, 3359, 0), new Position(3011, 3355, 0) };

	public static Position[] pathFailSafe1 = { new Position(3012, 3356, 0), new Position(3012, 3361, 0),
			new Position(3007, 3361, 0), new Position(3004, 3356, 0), new Position(3007, 3349, 0),
			new Position(3007, 3340, 0), new Position(3007, 3332, 0), new Position(3006, 3325, 0),
			new Position(3007, 3319, 0), new Position(3004, 3309, 0), new Position(3004, 3308, 0),
			new Position(3002, 3298, 0), new Position(3002, 3297, 0), new Position(3000, 3288, 0),
			new Position(2997, 3282, 0), new Position(2996, 3274, 0), new Position(2990, 3266, 0),
			new Position(2990, 3265, 0), new Position(2983, 3257, 0), new Position(2983, 3257, 0),
			new Position(2981, 3253, 0), new Position(2987, 3251, 0), new Position(2990, 3247, 0),
			new Position(2992, 3240, 0), new Position(2992, 3234, 0), new Position(2989, 3228, 0),
			new Position(2982, 3224, 0), new Position(2976, 3225, 0), new Position(2972, 3226, 0),
			new Position(2970, 3228, 0), new Position(2976, 3236, 0), new Position(2977, 3238, 0) };

	public static Position[] pathFailSafe2 = { new Position(3002, 3318, 0), new Position(2998, 3310, 0),
			new Position(2994, 3301, 0), new Position(2990, 3292, 0), new Position(2989, 3291, 0),
			new Position(2984, 3282, 0), new Position(2984, 3282, 0), new Position(2982, 3276, 0),
			new Position(2976, 3268, 0), new Position(2976, 3267, 0), new Position(2974, 3260, 0),
			new Position(2968, 3253, 0), new Position(2965, 3248, 0), new Position(2962, 3243, 0),
			new Position(2962, 3237, 0), new Position(2963, 3233, 0), new Position(2967, 3229, 0),
			new Position(2971, 3229, 0), new Position(2975, 3238, 0), new Position(2976, 3239, 0) };

	public static Position[] pathFailSafe3 = { new Position(3011, 3347, 0), new Position(3015, 3351, 0),
			new Position(3024, 3352, 0), new Position(3023, 3360, 0), new Position(3014, 3360, 0),
			new Position(3011, 3355, 0) };
}
