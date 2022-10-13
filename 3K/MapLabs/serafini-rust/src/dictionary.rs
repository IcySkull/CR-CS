pub struct Dictionary<'a> {
    words: Vec<&'a str>,
}

impl Dictionary<'_> {
    pub fn new(rawWords: String) -> Dictionary<'static> {
        Dictionary {
            words: rawWords.lines().collect(),
        }
    }

    pub fn contains(&self, target: &str) -> bool {
        self.words.contains(&target)
    }
}
