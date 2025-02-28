import random
import pandas as pd

VOWELS = "AEIOU"
CONSONANTS = "BCDFGKLMNPRSTXZ"
SEMIVOWELS = "HJVW"

all_probabilities = {
    # FILAS letra anterior COLUMNA letra siguiente: Por ejemplo C tiene 2 en la primer columna, es decir que es raro que TERMINE en C
      #., A,  E,  I,  O,  U,  B,  C,  D,  F,  G,  K,  L,  M,  N,  P,  R,  S,  T,  X,  Z,  H,  J,  V,  W
    '.': [0, 110, 50, 2, 20, 20, 130, 50, 60, 30, 60, 70, 70, 130, 110, 30, 50, 130, 60, 2, 70, 70, 40, 100, 60],
    'A': [170, 20, 2, 5, 2, 30, 40, 20, 80, 2, 20, 30, 20, 20, 70, 2, 70, 20, 70, 2, 80, 2, 190, 40, 30],
    'E': [40, 20, 2, 40, 40, 2, 20, 20, 2, 5, 40, 2, 150, 80, 100, 2, 130, 70, 60, 2, 5, 2, 30, 20, 30],
    'I': [30, 50, 2, 5, 2, 5, 20, 2, 5, 2, 30, 20, 40, 30, 20, 2, 30, 40, 20, 20, 20, 2, 20, 20, 20],
    'O': [2, 20, 20, 20, 2, 20, 2, 5, 30, 2, 5, 20, 50, 2, 30, 2, 60, 30, 30, 20, 2, 5, 2, 20, 30],
    'U': [2, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 20, 2, 30, 60, 2, 30, 30, 20, 2, 20, 20, 2, 20, 2],
    'B': [2, 70, 40, 30, 20, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 20, 2, 5, 2, 5, 2, 40, 2, 2],
    'C': [2, 20, 30, 20, 2, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 30, 2, 2],
    'D': [20, 40, 90, 2, 20, 20, 2, 5, 2, 5, 2, 5, 2, 5, 20, 2, 5, 2, 5, 2, 5, 2, 5, 20, 2],
    'F': [2, 2, 5, 2, 5, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2],
    'G': [20, 40, 2, 20, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 20, 40, 2, 5, 2],
    'K': [20, 70, 2, 20, 40, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 20, 2, 5, 2, 5, 2, 40, 2, 2],
    'L': [80, 60, 140, 2, 40, 2, 5, 2, 5, 2, 5, 2, 130, 2, 5, 2, 5, 20, 2, 5, 2, 5, 40, 2, 2],
    'M': [80, 50, 40, 50, 30, 30, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 20, 2, 5, 20, 2, 30, 2, 2],
    'N': [110, 80, 70, 40, 2, 40, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 30, 2, 5, 2, 5, 60, 20, 2],
    'P': [2, 2, 20, 2, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 2],
    'R': [80, 50, 80, 40, 20, 2, 20, 30, 2, 5, 2, 30, 2, 5, 2, 5, 2, 20, 20, 2, 20, 40, 20, 20, 2],
    'S': [130, 40, 70, 20, 20, 20, 20, 2, 5, 2, 5, 30, 2, 5, 2, 20, 2, 5, 40, 2, 5, 2, 5, 20, 2],
    'T': [20, 50, 2, 30, 2, 5, 2, 5, 2, 5, 2, 5, 20, 2, 5, 2, 5, 20, 2, 5, 2, 5, 80, 2, 50],
    'X': [2, 2, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 2],
    'Z': [30, 60, 60, 20, 2, 20, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 20, 20, 20, 20],
    'H': [20, 50, 40, 2, 20, 2, 5, 2, 5, 2, 5, 2, 20, 20, 2, 5, 20, 20, 2, 5, 2, 5, 2, 5, 20],
    'J': [280, 110, 100, 2, 50, 20, 2, 5, 2, 5, 2, 5, 20, 2, 5, 2, 5, 20, 2, 5, 20, 20, 2, 5, 2],
    'V': [40, 20, 30, 40, 50, 2, 5, 2, 5, 2, 5, 2, 20, 2, 30, 2, 30, 2, 5, 2, 5, 2, 20, 2, 2],
    'W': [40, 40, 20, 20, 2, 5, 2, 5, 50, 2, 5, 2, 5, 20, 20, 2, 5, 2, 5, 2, 5, 20, 40, 2, 2],
}
# Reglas de sufijos personalizadas
def apply_custom_rules(partial, probabilities):
    custom_rules = [
        {
            'trigger': ['T', 'H'],
            'chance': 0.04,
            'action': 'suffix',
            'value': 'WDES'
        },
        {
            'trigger': VOWELS + CONSONANTS + SEMIVOWELS,
            'chance': 0.05,
            'action': 'suffix',
            'value': 'AJ'
        },
        {
            'trigger': VOWELS,
            'chance': 0.01,
            'action': 'suffix',
            'value': 'LLA'
        }
        # PUEDO AGREGAR MAS REGLAS
    ]

    for rule in custom_rules:
        if partial[-1] in rule['trigger'] and random.random() < rule['chance']:
            if rule['action'] == 'suffix':
                return rule['value']
    
    return None

def generate_random_consonant():
    return random.choice(CONSONANTS)

def generate_random_vowel():
    return random.choice(VOWELS)

def generate_random_semivowel():
    return random.choice(SEMIVOWELS)

# genero una letra que no sea vocal
def generate_random_not_vowel():
    return random.choice([generate_random_consonant, generate_random_semivowel])()

def generate_random_letter():
    return random.choice([generate_random_vowel, generate_random_not_vowel])()

def should_end_name(partial):
    """Determina si se debe generar un punto para terminar el nombre."""
    length = len(partial)
    if length <= 2:
        return False
    elif length == 3:
        return random.random() < 0.01
    elif length == 4:
        return random.random() < 0.02
    elif length == 5:
        return random.random() < 0.03
    elif length == 6:
        return random.random() < 0.035
    elif length == 7:
        return random.random() < 0.04
    elif length == 8:
        return random.random() < 0.04
    elif length == 9:
        return random.random() < 0.05
    elif length == 10:
        return random.random() < 0.2
    else:
        return random.random() < 1 - (1 / (length - 10))

def generate_letter(partial="."):
    last_letter = partial[-1]
    probabilities = all_probabilities.get(last_letter, [0.2] * 25)

    probabilities = adjust_probabilities(partial, probabilities)

    custom_result = apply_custom_rules(partial, probabilities)
    if custom_result:
        # print("GENERADO!: " + partial + custom_result)
        return custom_result + "."
  

    total_probability = sum(probabilities)
    if total_probability == 0:
        probabilities = [1] * 25
        total_probability = 25
      
    normalized_probabilities = [p / total_probability for p in probabilities]
    population = "." + VOWELS + CONSONANTS + SEMIVOWELS
  
    chosen_letter = random.choices(population, weights=normalized_probabilities, k=1)[0]

    if should_end_name(partial) and len(partial) > 2:
        return "."
  
    # Si se elige un punto y no hay vocales en la palabra, forzar una vocal
    if chosen_letter == "." and not any(letter in VOWELS for letter in partial):
        vowel_probabilities = [normalized_probabilities[i] for i in range(1, 6)]
        total_vowel_probability = sum(vowel_probabilities)
        if total_vowel_probability == 0:
            vowel_probabilities = [1] * 5
            total_vowel_probability = 5
        normalized_vowel_probabilities = [p / total_vowel_probability for p in vowel_probabilities]
        chosen_letter = random.choices(VOWELS, weights=normalized_vowel_probabilities, k=1)[0]

    while chosen_letter == "." and len(partial) <= 2:
        chosen_letter = random.choices(population, weights=normalized_probabilities, k=1)[0]
        
    return chosen_letter

def adjust_probabilities(partial, probabilities):
    LETTER_INDEX = {letter: i for i, letter in enumerate("." + VOWELS + CONSONANTS + SEMIVOWELS)}
    
    def reset_probabilities(letter_set):
        for letter in letter_set:
            probabilities[LETTER_INDEX[letter]] = 0

    # Regla 1: Si las últimas 3 letras son consonantes, fuerza vocal o semivocal
    if len(partial) >= 3 and all(l in CONSONANTS for l in partial[-3:]):
        reset_probabilities(CONSONANTS)

    # Regla 2: Si las últimas 4 letras no contienen una vocal, fuerza vocal
    if len(partial) >= 4 and not any(l in VOWELS for l in partial[-4:]):
        reset_probabilities(CONSONANTS + SEMIVOWELS)

    # Regla 3: Si las últimas 2 letras son vocales, fuerza consonante o semivocal
    elif len(partial) >= 2 and all(l in VOWELS for l in partial[-2:]):
        reset_probabilities(VOWELS)

    # Regla 4: Una palabra no puede empezar con 4 letras que no sean vocales
    if len(partial) == 3 and not any(l in VOWELS for l in partial):
        reset_probabilities(CONSONANTS + SEMIVOWELS)

    return probabilities

def generate_name():
    name = generate_letter() # Generar la primera letra
    while name[-1] != ".": # Mientras la última letra no sea un punto
        name += generate_letter(name) # Generar la siguiente letra
    # Eliminar el último carácter (el punto ".")
    name = name[:-1]
    return name

def generate_names(quant):
    names = [generate_name() for _ in range(quant)]
    return names

# esta funcion es para crear el archivo excel con los nombres YA USADA
def nombres_bilsa(quant):
    #nombres = generate_names(quant)
    nombres = set()  # USO SET PARA QUE NO SE REPITAN
    while len(nombres) < quant:
        nombres.update(generate_names(quant - len(nombres)))

    nombres = list(nombres)  # lista para DataFrame
    df = pd.DataFrame({"Nombres": nombres})
    df.to_excel("nombres.xlsx", index=False, engine="openpyxl")
    print(f"Archivo 'nombres.xlsx' guardado con {quant} nombres.")

# nombres_bilsa(88912)