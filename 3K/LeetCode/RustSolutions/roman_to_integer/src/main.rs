fn main() {
    println!("{}", roman_to_int(String::from("MCMXCIV")));
}

pub fn roman_to_int(s: String) -> i32 {
    let mut chars = s.chars().rev();
    let mut prev = chars.next().unwrap();
    return chars.fold(roman_value(prev), |acc, c| {
        let c_value = roman_value(c);
        let prev_value = roman_value(prev);
        prev = c;
        if c_value < prev_value {
            return acc - c_value;
        } else {
            return acc + c_value;
        }
    });
}

pub fn roman_value(c: char) -> i32 {
    return match c {
        'I' =>  1,
        'V' =>  5,
        'X' =>  10, 
        'L' => 50,
        'C' => 100,
        'D' => 500,
        'M' => 1000,
        _ => 0,
    }
}