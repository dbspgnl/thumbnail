let jsonData = [{
	"title":"content",
	"status": 1,
	"children":[
		{
			"title":"Guidance",
			"status": 1,
			"children":[
				{
					"title":"TSG",
					"status": 1,
					"children":[
						{
							"title":"EPOS",
							"status": 1,
							"children":[]
						},
						{
							"title":"ECU",
							"status": 1,
							"children":[]
						},
						{
							"title":"TCU",
							"status": 1,
							"children":[]
						}
					]
				},
				{
					"title":"SSM",
					"status": 1,
					"children":[]
				},
				{
					"title":"PFT",
					"status": 1,
					"children":[]
				}
			]
		},
		{
			"title":"Model Viewer",
			"status": 1,
			"children":[
				{
					"title":"HYD",
					"status": 1,
					"children":[]
				},
				{
					"title":"ELEC",
					"status": 1,
					"children":[]
				},
				{
					"title":"ENG",
					"status": 1,
					"children":[]
				}
			]
		}
	]
}]

// let jsonData = {
// 	"content": [
// 		{
// 			"Guidance": {
// 				"status": 1,
// 				"value": [
// 					{
// 						"TSG": {
// 							"status": 1,
// 							"value": [
// 								{
// 									"EPOS": {
// 										"status": 1,
// 										"value": []
// 									}
// 								},
// 								{
// 									"ECU": {
// 										"status": 1,
// 										"value": []
// 									}
// 								},
// 								{
// 									"TCU": {
// 										"status": 1,
// 										"value": []
// 									}
// 								}
// 							]
// 						}
// 					},
// 					{
// 						"SSM": {
// 							"status": 1,
// 							"value": []
// 						}
// 					},
// 					{
// 						"PFT": {
// 							"status": 1,
// 							"value": []
// 						}
// 					}
// 				]
// 			}
// 		},
// 		{
// 			"Model Viewer": {
// 				"status": 1,
// 				"value": [
// 					{
// 						"HYD": {
// 							"status": 1,
// 							"value": []
// 						}
// 					},
// 					{
// 						"ELEC": {
// 							"status": 1,
// 							"value": []
// 						}
// 					},
// 					{
// 						"ENG": {
// 							"status": 1,
// 							"value": []
// 						}
// 					}
// 				]
// 			}
// 		}
// 	]
// }

// let jsonData = [
// 	{"title": "Books", "expanded": true, "folder": true, "children": [
// 		{"title": "Art of War", "type": "book", "author": "Sun Tzu", "year": -500, "qty": 21, "price": 5.95},
// 		{"title": "The Hobbit", "type": "book", "author": "J.R.R. Tolkien", "year": 1937, "qty": 32, "price": 8.97},
// 		{"title": "The Little Prince", "type": "book", "author": "Antoine de Saint-Exupery", "year": 1943, "qty": 21, "price": 5.95},
// 		{"title": "Don Quixote", "type": "book", "author": "Miguel de Cervantes", "year": 1615, "qty": 21, "price": 5.95}
// 	]},
// 	{"title": "Music", "folder": true, "children": [
// 		{"title": "Nevermind", "type": "music", "author": "Nirvana", "year": 1991, "qty": 21, "price": 5.95, "selected": true},
// 		{"title": "Autobahn", "type": "music", "author": "Kraftwerk", "year": 1974, "qty": 21, "price": 5.95},
// 		{"title": "Kind of Blue", "type": "music", "author": "Miles Davis", "year": 1959, "qty": 21, "price": 5.95},
// 		{"title": "Back in Black", "type": "music", "author": "AC/DC", "year": 1980, "qty": 21, "price": 5.95},
// 		{"title": "The Dark Side of the Moon", "type": "music", "author": "Pink Floyd", "year": 1973, "qty": 21, "price": 5.95},
// 		{"title": "Sgt. Pepper's Lonely Hearts Club Band", "type": "music", "author": "The Beatles", "year": 1967, "qty": 21, "price": 5.95}
// 	]},
// 	{"title": "Electronics & Computers", "expanded": true, "folder": true, "children": [
// 		{"title": "Cell Phones", "folder": true, "children": [
// 			{"title": "Moto G", "type": "phone", "author": "Motorola", "year": 2014, "qty": 21, "price": 224.99},
// 			{"title": "Galaxy S8", "type": "phone", "author": "Samsung", "year": 2016, "qty": 21, "price": 509.99},
// 			{"title": "iPhone SE", "type": "phone", "author": "Apple", "year": 2016, "qty": 21, "price": 282.75},
// 			{"title": "G6", "type": "phone", "author": "LG", "year": 2017, "qty": 21, "price": 309.99},
// 			{"title": "Lumia", "type": "phone", "author": "Microsoft", "year": 2014, "qty": 21, "price": 5.95},
// 			{"title": "Xperia", "type": "phone", "author": "Sony", "year": 2014, "qty": 21, "price": 5.95},
// 			{"title": "3210", "type": "phone", "author": "Nokia", "year": 1999, "qty": 21, "price": 5.95}
// 		]},
// 		{"title": "Computers", "folder": true, "children": [
// 			{"title": "ThinkPad", "type": "computer", "author": "IBM", "year": 1992, "qty": 21, "price": 749.90},
// 			{"title": "C64", "type": "computer", "author": "Commodore", "year": 1982, "qty": 21, "price": 595.00},
// 			{"title": "MacBook Pro", "type": "computer", "author": "Apple", "year": 2006, "qty": 21, "price": 1949.95},
// 			{"title": "Sinclair ZX Spectrum", "type": "computer", "author": "Sinclair Research", "year": 1982, "qty": 21, "price": 529},
// 			{"title": "Apple II", "type": "computer", "author": "Apple", "year": 1977, "qty": 21, "price": 1298},
// 			{"title": "PC AT", "type": "computer", "author": "IBM", "year": 1984, "qty": 21, "price": 1235.00}
// 		]}
// 	]},
// 	{"title": "More...", "folder": true, "lazy": true}
// ]