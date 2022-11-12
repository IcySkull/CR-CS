fn main() {}

pub fn longest_common_prefix(strs: Vec<String>) -> String {
    let min_str = strs.iter().min_by_key(|s| s.len()).unwrap();
    for (i, c) in min_str.chars().enumerate() {
        for s in &strs {
            if s.chars().nth(i).unwrap() != c {
                return String::from(&min_str[0..i]);
            }
        }
    }
    min_str.clone()
}

pub fn longest_common_prefix2(strs: Vec<String>) -> String {
    let min_str = strs.iter().min_by_key(|s| s.len()).unwrap();
    for (i, c) in min_str.chars().enumerate() {
        if strs.iter().any(|s| s.chars().nth(i).unwrap() != c) {
            return String::from(&min_str[0..i]);
        }
    }
    min_str.clone()
}
