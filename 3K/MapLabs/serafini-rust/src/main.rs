use std::fs;
use dictionary::Dictionary;
mod dictionary;

fn main() {
    let dictionary = match fs::read_to_string("EnglishWords.txt") {
        Ok(words) => words,
        Err(e) => println!("An error occurred while reading the file: {}", e),
    };
}