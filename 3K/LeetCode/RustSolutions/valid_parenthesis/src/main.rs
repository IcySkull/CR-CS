fn main() {
    print!("{}", is_valid("()[[".to_string()));
}

pub fn is_valid(s: String) -> bool {
    let closed_open = std::collections::HashMap::from([
        (')', '('),
        (']', '['),
        ('}', '{')
    ]);

    if s.len() % 2 == 1 {return false}

    match s.chars().try_fold(Vec::new(), |mut acc, next| {
        match closed_open.get(&next) {
            Some(open) => {
                if let Some(c) = acc.pop() {
                    if c != *open {Err(acc)} else {Ok(acc)}
                } 
                else {Err(acc)}
            },
            None => {
                acc.push(next);
                Ok(acc)
            }
        }
    }) {
        Ok(v) => v.is_empty(),
        Err(_) => false,
    }
}